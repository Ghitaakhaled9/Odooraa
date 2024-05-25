package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/contact")
    public String afficherContact(Model model) {

        return "Contact";
    }

}
