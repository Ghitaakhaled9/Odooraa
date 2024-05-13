package com.example.Odooraa.Controller;

import com.example.Odooraa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MDPController {
    private final UserService userService;

    @Autowired
    public MDPController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/motdepasse")
    public String showLoginPage() {
        return "MotDePasseOublie"; // Assuming you have a login.html file in your templates directory
    }

    @PostMapping("/motdepasse/update")
    public String updateMotDePasse(@RequestParam("username") String username,
                                   @RequestParam("password") String oldPassword,
                                   @RequestParam("password1") String newPassword) {
        if (userService.updatePassword(username, oldPassword, newPassword)) {
            return "redirect:/index";
        } else {
            return "redirect:/error";
        }
    }
}
