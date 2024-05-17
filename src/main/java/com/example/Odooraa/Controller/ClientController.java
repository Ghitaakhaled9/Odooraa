package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ClientController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String afficherUsersWebsite(Model model) {
            var utilisateurs = userRepository.findByType(UserType.CLIENT);
            model.addAttribute("utilisateurs", utilisateurs);
            return "Clients";
        }

}

