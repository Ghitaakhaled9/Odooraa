package com.example.Odooraa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @GetMapping("/produits/error")
    public String showError(Model model) {
        model.addAttribute("errorMessage", "Il y a eu un problème avec votre requête. Veuillez réessayer.");
        return "errorPage"; // Assurez-vous que le fichier de vue "errorPage.html" existe dans vos ressources
    }

}
