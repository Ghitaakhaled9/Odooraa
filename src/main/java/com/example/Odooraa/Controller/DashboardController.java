package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.CommandeRepository;
import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/dashbord")
    public String afficherDashboard(Model model) {
        long nombreCommandes = commandeRepository.count();
        long nombreClients = userRepository.countByType(UserType.CLIENT);
        double totalAchats = produitRepository.count();
        long nombreProdQuantNull = produitRepository.countByQuantityProductZero();

        model.addAttribute("nombreCommandes", nombreCommandes);
        model.addAttribute("nombreClients", nombreClients);
        model.addAttribute("totalAchats", totalAchats);
        model.addAttribute("nombreProdQuantNull", nombreProdQuantNull);
        return "dashbord";
    }
}

