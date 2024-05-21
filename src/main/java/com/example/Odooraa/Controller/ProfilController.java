package com.example.Odooraa.Controller;

import com.example.Odooraa.entities.UserSite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String showProfileClientPage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserSite user = (UserSite) session.getAttribute("user");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("sexe", user.getSexe());
            model.addAttribute("adresse", user.getAdresse());
            model.addAttribute("tel", user.getTel());
        }
        return "profil";
    }
}
