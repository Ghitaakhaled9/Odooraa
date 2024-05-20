package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;

@Controller
public class singleProductController {
    private final ProduitRepository produitRepository;

    @Autowired
    public singleProductController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/single/{id}")
    public String showSingleProductPage(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit != null) {
            model.addAttribute("product", produit);
            return "single";
        } else {
            return "redirect:/index"; // Redirect to home page if product not found
        }
    }
}
