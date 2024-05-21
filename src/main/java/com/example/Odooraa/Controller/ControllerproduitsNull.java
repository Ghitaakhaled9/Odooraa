package com.example.Odooraa.Controller;


import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class ControllerproduitsNull {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/produitsNull")
    public String afficherProduitsAvecQuantiteNulle(Model model) {
        List<Produit> produits = produitRepository.findByQuantityProduct(0);
        model.addAttribute("produits", produits);
        return "ProduitQuantitéNull"; // Assuming you have a Thymeleaf template named "Clients.html"
    }
}
