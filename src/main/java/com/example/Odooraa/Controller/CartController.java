package com.example.Odooraa.controllers;

import com.example.Odooraa.entities.Panier;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.services.CartService;
import com.example.Odooraa.services.ProductService;
import com.example.Odooraa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, @RequestParam Long userId) {
        cartService.addToCart(productId, quantity, userId);
        return "redirect:/cart"; // Redirect to the cart page after adding the product
    }
}
