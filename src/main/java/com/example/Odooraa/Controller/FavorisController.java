package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Odooraa.Service.FavorisService;
import com.example.Odooraa.Service.PanierService;
import com.example.Odooraa.Service.ProduitService;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.Favoris;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.entities.UserSite;

import jakarta.servlet.http.HttpSession;

@Controller
public class FavorisController {

    @Autowired
    private FavorisService favorisService;
    private UserService userService;
    private PanierService panierService;
    private ProduitService produitService;

    @Autowired
    public FavorisController(FavorisService favorisService, UserService userService, PanierService panierService,
                             ProduitService produitService) {
        this.favorisService = favorisService;
        this.userService = userService;
        this.panierService = panierService;
        this.produitService = produitService;
    }

    @GetMapping("/favoris")
    public String listFavoris(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserSite userSession = (UserSite) session.getAttribute("user");
            UserSite user = userService.getUserById(userSession.getId());
            Favoris favorisSession = user.getFavoris();

            model.addAttribute("listFavoris", favorisSession.getProduits());
            System.out.print("jjjjjjj");
            int nombreProduits = 0;
            int nombreFavoris = 0;

            nombreProduits = user.getPanier().getProduits().size();
            model.addAttribute("nombreProduits", nombreProduits);
            System.out.println("hhhhhhhhhhhhh" + nombreProduits);
            float totalPrice = 0;
            for (Produit product : user.getPanier().getProduits()) {
                totalPrice += product.getPrix();
            }
            nombreFavoris = user.getFavoris().getProduits().size();
            model.addAttribute("nombreFavoris", nombreFavoris);
        }

        return "favoris";
    }

    @GetMapping("/deleteProductFavoris/{productId}")
    public String deleteProductFromCart(@PathVariable Long productId, HttpSession session) {
        UserSite userSession = (UserSite) session.getAttribute("user");
        UserSite user = userService.getUserById(userSession.getId());
        favorisService.removeProductFromCart(productId, user.getFavoris().getId());

        return "redirect:/favoris";
    }

    @PostMapping("/addToPanier")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        Produit produit = produitService.getProduitById(productId);
        UserSite userSession = (UserSite) session.getAttribute("user");
        UserSite user = userService.getUserById(userSession.getId());
        panierService.ajouterProduitAuPanier(user.getPanier().getId(), produit);
        favorisService.removeProductFromCart(productId, userSession.getFavoris().getId());
        return "redirect:/favoris";
    }
}