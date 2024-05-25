package com.example.Odooraa.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;

@Controller
public class ShopNowController {
    private final ProduitRepository produitRepository;

    // Constructor injection of ProduitRepository
    public ShopNowController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/categories")
    public String shopNow(Model model) {
        // Retrieve all products from the database
        List<Produit> products = produitRepository.findAll();
        // Add the products to the model
        model.addAttribute("products", products);
        // Return the name of the Thymeleaf template
        return "categories";
    }
}