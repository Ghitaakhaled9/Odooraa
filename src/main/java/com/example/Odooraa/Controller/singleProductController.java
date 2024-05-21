package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;

@Controller
public class singleProductController {

    private final ProduitRepository produitRepository;

    @Autowired
    public singleProductController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/single/{id}") // Add path variable for product ID
    public String showSingleProductPage(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findById(id).orElse(null);
        
        if (produit != null) {
            model.addAttribute("product", produit);
            return "single"; // Return the view name for the single product page
        } else {
            // Handle the case where the product is not found
            return "error"; // Assuming you have a view named "productNotFound"
        }
    }
    
}
