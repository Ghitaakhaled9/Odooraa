package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Odooraa.Service.FavorisService;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.entities.User;

@Controller
public class FavorisController {

    @Autowired
    private FavorisService favorisService;
    private UserService userService;

    @Autowired
    public FavorisController(FavorisService favorisService, UserService userService) {
        this.favorisService = favorisService;
        this.userService = userService;
    }

    @GetMapping("/favoris")
    public String listFavoris(Model model) {
        model.addAttribute("lists", favorisService.getAllFavoriss());
        System.out.print("jjjjjjj");
        int nombreProduits = 0;
        User user = userService.getUserById(1L);
        nombreProduits = user.getFavoris().getProduits().size();
        model.addAttribute("nombreProduits", nombreProduits);
        System.out.println("hhhhhhhhhhhhh" + nombreProduits);
        float totalPrice = 0;
        for (Produit product : user.getPanier().getProduits()) {
            totalPrice += product.getPrix();
        }
        model.addAttribute("total", totalPrice);
        return "favoris";
    }
}
