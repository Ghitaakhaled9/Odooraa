package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class SingleProductController {
    
    private final ProduitRepository produitRepository;

    @Autowired
    public SingleProductController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    
    @GetMapping("/single/{id}") // Add path variable for product ID
    public String showSingleProductPage(@PathVariable Long id, Model model) {
<<<<<<< HEAD
        Produit product = produitRepository.findById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            return "single"; // Return the view name for the single product page
=======
        Produit produit = produitRepository.findById(id).orElse(null);
        
        if (produit != null) {
            model.addAttribute("product", produit);
            return "single"; // Ensure you have a view named "single"
>>>>>>> 4ca88e88e9dc77815feaf1e6ce26b30dc34bace0
        } else {
            // Handle the case where the product is not found
            return "productNotFound"; // Assuming you have a view named "productNotFound"
        }
    }
}
