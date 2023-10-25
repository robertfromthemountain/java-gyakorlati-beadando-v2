package com.example.securityrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/home")
    public String user() {
        return "user";
    }



    @GetMapping("/admin/home")
    public String admin(Model model) {
        return "admin";
    }
    @Autowired private UzenetRepo uzenetRepo;
    @GetMapping("/admin/uzenetek")
    public String uzenetek(Model model){
        List<Uzenet> uzenets = uzenetRepo.findAllOrderByCreatedAtDesc();
        model.addAttribute("uzenets", uzenets);
        return "uzenetek";
    }

    //PIZZÁK RÉSZ
    @Autowired private PizzaRepo pizzaRepo;
    @GetMapping("/admin/pizza")
    public String pizza(Model model){
        List<Pizza> pizzas = new ArrayList<>();
        for (Pizza pizza : pizzaRepo.findAll()){
            pizzas.add(pizza);
        }
        model.addAttribute("pizzas", pizzas);
        return "pizza";
    }

    //RENDELÉSEK RÉSZ
    @Autowired private RendelesRepo rendelesRepo;
    @GetMapping("/admin/rendeles")
    public String rendeles(Model model){
        List<Rendeles> rendelesek = new ArrayList<>();
        for (Rendeles rendeles : rendelesRepo.findAll()){
            rendelesek.add(rendeles);
        }
        model.addAttribute("rendelesek", rendelesek);
        return "rendeles";
    }

    //KATEGÓRIA RÉSZ
    @Autowired private KategoriaRepo kategoriaRepo;
    @GetMapping("/admin/kategoria")
    public String kategoria(Model model){
        List<Kategoria> kategoriak = new ArrayList<>();
        for (Kategoria kategoria:kategoriaRepo.findAll()){
            kategoriak.add(kategoria);
        }
        model.addAttribute("kategoriak", kategoriak);
        return "kategoria";
    }

    @GetMapping("/regisztral")
    public String greetingForm(Model model) {
        model.addAttribute("reg", new User());
        return "regisztral";
    }
    @Autowired
    private UserRepository userRepo;
    @PostMapping("/regisztral_feldolgoz")
    public String Regisztráció(@ModelAttribute User user, Model model) {
        for(User felhasznalo2: userRepo.findAll())
            if(felhasznalo2.getEmail().equals(user.getEmail())){
                model.addAttribute("uzenet", "A regisztrációs email már foglalt!");
                return "reghiba";
            }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
// Regisztrációkor minden felhasználónak Vendég szerepet adunk:
        user.setRole("ROLE_Vendeg");
        userRepo.save(user);
        model.addAttribute("id", user.getId());
        return "regjo";
    }

    @GetMapping("/kapcsolat")
    public String kapcsolat(Model model) {
        model.addAttribute("kapcs", new Kapcsolat());
        return "kapcsolat";
    }
    @Autowired private KapcsolatRepo kapcsolatRepo;
    @PostMapping(value="/ment")
    public String mentKapcs(@ModelAttribute Kapcsolat kapcsolat, RedirectAttributes redirAttr, String username, Authentication authentication){
        kapcsolat.setFelado("Guest");
        if (authentication != null && authentication.isAuthenticated()){
            kapcsolat.setFelado(authentication.getName());
        }

        kapcsolatRepo.save(kapcsolat);
        redirAttr.addFlashAttribute("uzenet","Az üzenet küldése sikeres! ID="+kapcsolat.getId());
        return "redirect:/";
    }

}
