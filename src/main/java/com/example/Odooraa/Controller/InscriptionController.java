package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import com.example.Odooraa.entities.sexe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InscriptionController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/inscription")
    public String afficherPageInscription() {
        return "inscription";
    }


    @PostMapping("/inscription/add")
    public String ajouterClient(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("sexe") sexe sexe,
            @RequestParam("adresse") String adresse,
            @RequestParam("tel") String tel,
            @RequestParam("email") String email

    ) {
        UserSite user = new UserSite();
        user.setUsername(username);
        user.setPassword(password);
        user.setSexe(sexe);
        user.setAdresse(adresse);
        user.setTel(tel);
        user.setEmail(email);
        user.setType(UserType.CLIENT);

        userRepository.save(user);

        return "redirect:/index";
    }

}
