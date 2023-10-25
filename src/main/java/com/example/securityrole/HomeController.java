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
    public String admin(Model model, String uzenet) {
        String str = A();
        model.addAttribute("str", str);
        return "admin";
    }
    String A(){
        String str="";
        for (Pizza pizza: pizzaRepo.findAll()){
            str+=pizza.getNev()+"; "+pizza.getKategorianev()+"; "+pizza.isVegetarianus();
            str+="<br>";
        }
        str+="<br>";
        for (Kategoria kategoria: kategoriaRepo.findAll()){
            str+=kategoria.getNev()+"; "+kategoria.getSzam();
            str+="<br>";
        }
        str+="<br>";
        for (Rendeles rendeles: rendelesRepo.findAll()){
            str+=rendeles.getAz()+"; "+rendeles.getPizzanev()+"; "+rendeles.getDarab()+"; "+rendeles.getFelvetel()+"; "+rendeles.getKiszallitas();
            str+="<br>";
        }
        return str;
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
