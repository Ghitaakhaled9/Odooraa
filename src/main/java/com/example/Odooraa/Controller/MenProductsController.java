package com.example.Odooraa.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;

@Controller
public class MenProductsController {

    private final ProduitRepository produitRepository;

    // Constructor injection of ProduitRepository
    public MenProductsController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/Men")
    public String showMensProducts(Model model) {
        // Retrieve men's products from the database
        List<Produit> mensProducts = produitRepository.findByCategory("Men");
        // Add the men's products to the model
        model.addAttribute("products", mensProducts);
        // Return the name of the Thymeleaf template
        return "categories";
    }
}