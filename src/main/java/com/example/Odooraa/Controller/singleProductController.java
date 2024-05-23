package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SingleProductController {
    
    private final ProduitRepository produitRepository;

    @Autowired
    public SingleProductController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    
    @GetMapping("/single/{id}") // Add path variable for product ID
    public String showSingleProductPage(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findById(id).orElse(null);
        
        if (produit != null) {
            model.addAttribute("product", produit);
            return "single"; // Ensure you have a view named "single"
        } else {
            // Handle the case where the product is not found
            return "productNotFound"; // Assuming you have a view named "productNotFound"
        }
    }
}
