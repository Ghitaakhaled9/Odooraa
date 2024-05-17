package com.example.Odooraa.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class logoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Récupérer la session
        HttpSession session = request.getSession(false);

        // Si la session existe, l'invalider
        if (session != null) {
            session.invalidate();
        }

        // Rediriger vers la page de connexion ou une autre page après la déconnexion
        return "redirect:/login"; // Assurez-vous que "/login" correspond à votre page de connexion
    }
}
