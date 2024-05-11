package com.example.Odooraa.Controller;


import com.example.Odooraa.Repository.UserRepository;

import com.example.Odooraa.Service.UserService;

import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import com.example.Odooraa.entities.sexe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GerantController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/gerant")
    public String afficherUsers(Model model) {
        var utilisateurs = userRepository.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "Gerant";
    }


    @PostMapping("/gerant/add")
    public String ajouterGerant(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("sexe") sexe sexe, // Change the type to Sexe enum
            @RequestParam("adresse") String adresse,
            @RequestParam("tel") String tel,
            @RequestParam("email") String email,
            @RequestParam("Type") UserType type
    ) {
        UserSite user = new UserSite();
        user.setUsername(username);
        user.setPassword(password);
        user.setSexe(sexe); // Set the enum value directly
        user.setAdresse(adresse);
        user.setTel(tel);
        user.setEmail(email);
        user.setType(type);

        userRepository.save(user);

        return "redirect:/gerant"; // Redirection après l'ajout
    }



    @GetMapping("/gerant/delete/{id}")
    public String deleteGerant(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/gerant";
    }


}
