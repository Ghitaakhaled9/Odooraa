package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Odooraa.Service.FavorisService;
import com.example.Odooraa.Service.PanierService;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.entities.UserSite;

import org.springframework.ui.Model;

@Controller
public class CheckController {

    @Autowired

    private UserService userService;

    @Autowired
    public CheckController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/check")
    public String listFavoris(@RequestParam("subtotal") String subtotalValue,
            @RequestParam("total") String totalValue,
            Model model) {

        int nombreProduits = 0;
        int nombreFavoris = 0;
        UserSite user = userService.getUserById(1L);
        nombreProduits = user.getPanier().getProduits().size();
        model.addAttribute("nombreProduits", nombreProduits);
        System.out.println("hhhhhhhhhhhhh" + nombreProduits);

        nombreFavoris = user.getFavoris().getProduits().size();
        model.addAttribute("nombreFavoris", nombreFavoris);
        System.out.println("sub" + subtotalValue);
        model.addAttribute("subtotal", subtotalValue);
        model.addAttribute("total", totalValue);

        return "check";
    }

}
