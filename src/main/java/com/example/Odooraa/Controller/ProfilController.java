package com.example.Odooraa.Controller;

import com.example.Odooraa.entities.UserSite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String showProfilePage(Model model, HttpSession session) {
        // Vérifiez d'abord si l'utilisateur est connecté en vérifiant s'il y a des informations d'utilisateur dans la session
        if (session.getAttribute("user") != null) {
            // Si l'utilisateur est connecté, récupérez ses informations de session
            UserSite user = (UserSite) session.getAttribute("user");
            // Transférez les informations de l'utilisateur au modèle pour les afficher dans le formulaire
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("sexe", user.getSexe());
            model.addAttribute("adresse", user.getAdresse());
            model.addAttribute("tel", user.getTel());
        }
        // Retourne le nom de la page à afficher (dans votre cas, "profil")
        return "profil"; // Assurez-vous que "profil" correspond au nom de votre fichier HTML/Thymeleaf
    }
}
