package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerUrl {
    
    private final ProduitRepository produitRepository;

    @Autowired
    public ControllerUrl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        var products = produitRepository.findAll();
        model.addAttribute("products", products);
        return "index"; // Ensure you have a view named "index"
    }
}
