package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ControllerUrl {
    
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        // Retrieve all products from the repository
        var products = produitRepository.findAll();
        // Add products to the model
        model.addAttribute("products", products);
        // Return the view name
        return "index";
    }

}
