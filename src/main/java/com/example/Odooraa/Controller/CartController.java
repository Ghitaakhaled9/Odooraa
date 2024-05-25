package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Odooraa.Service.PanierService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private final PanierService panierService;
    private final HttpSession httpSession;

    @Autowired
    public CartController(PanierService panierService, HttpSession httpSession) {
        this.panierService = panierService;
        this.httpSession = httpSession;
    }

    // Other controller methods...

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestParam("productId") Long productId) {
        try {
            // Get the user's cart ID from the session
            Long cartId = (Long) httpSession.getAttribute("cartId");

            if (cartId == null) {
                // If the user is not logged in, redirect to the login page
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to add items to your cart");
            }

            panierService.ajouterProduitAuPanierLongPram(cartId, productId);
            return ResponseEntity.ok("Product added to cart");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product to cart");
        }
    }
}