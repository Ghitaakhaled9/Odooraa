package com.example.Odooraa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;

import java.util.List;

@Controller
public class WomenCategoryController {

    private final ProduitRepository produitRepository;

    public WomenCategoryController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/Women")
    public String showWomensProducts(Model model) {
        // Retrieve women's products from the database
        List<Produit> womensProducts = produitRepository.findByCategory("Women");
        // Add the women's products to the model
        model.addAttribute("products", womensProducts);
        // Return the name of the Thymeleaf template
        return "categories";
    }
}