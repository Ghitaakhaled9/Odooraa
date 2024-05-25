package com.example.Odooraa.Controller;

import com.example.Odooraa.Service.ProduitService;
import com.example.Odooraa.entities.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerUrl {

    private final ProduitService produitService;

    @Autowired
    public ControllerUrl(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        // Retrieve all products
        List<Produit> allProducts = produitService.getAllProduits();
        model.addAttribute("allProducts", allProducts);

        // Retrieve top best-selling products
        List<Produit> bestSellingProducts = produitService.getTopBestSellingProducts(10); // Change 10 to the desired number of top products
        model.addAttribute("bestSellingProducts", bestSellingProducts);

        return "index"; // Ensure you have a view named "index"
    }
}