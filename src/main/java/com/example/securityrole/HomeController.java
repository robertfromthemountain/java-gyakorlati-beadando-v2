package com.example.securityrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired private PizzaRepo pizzaRepo;
    @Autowired private KategoriaRepo kategoriaRepo;
    @Autowired private RendelesRepo rendelesRepo;
    @GetMapping("/admin/home")
    public String admin(Model model) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Pizza pizza : pizzaRepo.findAll()){
            pizzas.add(pizza);
        }
        model.addAttribute("pizzas", pizzas);

        List<Rendeles> rendelesek = new ArrayList<>();
        for (Rendeles rendeles:rendelesRepo.findAll()){
            rendelesek.add(rendeles);
        }
        model.addAttribute("rendelesek", rendelesek);

        List<Kategoria> kategoriak = new ArrayList<>();
        for (Kategoria kategoria:kategoriaRepo.findAll()){
            kategoriak.add(kategoria);
        }
        model.addAttribute("kategoriak", kategoriak);
        return "admin";
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

}
