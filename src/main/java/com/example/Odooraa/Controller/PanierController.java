package com.example.Odooraa.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Odooraa.Service.PanierService;
import com.example.Odooraa.Service.UserService;
import com.example.Odooraa.entities.Panier;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.entities.UserSite;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PanierController {

    @Autowired
    private PanierService panierService;
    private UserService userService;

    @Autowired
    public PanierController(PanierService panierService, UserService userService) {
        this.panierService = panierService;
        this.userService = userService;
    }

    @GetMapping("/oriPanier")
    public String listPanier(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserSite userSession = (UserSite) session.getAttribute("user");
            UserSite user = userService.getUserById(userSession.getId());
            Panier panierSession = user.getPanier();
            model.addAttribute("listPanier", panierSession.getProduits());
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

            model.addAttribute("total", totalPrice);
        }

        return "oriPanier";

    }

    @PostMapping("/updateCart")
    public String updateCart(HttpServletRequest request, Model model) {

        int quantity = Integer.parseInt(request.getParameter("quantities"));
        float productPrix = Float.parseFloat(request.getParameter("productPrix"));
        float total = Float.parseFloat(request.getParameter("total"));

        float newTotal = total - productPrix + (productPrix * quantity);
        System.out.println("Nouveau total : " + newTotal);
        model.addAttribute("total", newTotal);
        // Mettez à jour votre panier ou effectuez d'autres opérations nécessaires ici
        return "redirect:/panier";
    }

    @GetMapping("/new")
    public String showPanierForm(Model model) {
        model.addAttribute("panier", new Panier());
        return "new_panier";
    }

    @PostMapping("/save")
    public String savePanier(@ModelAttribute("panier") Panier panier) {
        panierService.createPanier(panier);
        return "redirect:/panier/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Panier panier = panierService.getPanierById(id);
        model.addAttribute("panier", panier);
        return "edit_panier";
    }

    @PostMapping("/update/{id}")
    public String updatePanier(@PathVariable Long id, @ModelAttribute("panier") Panier panier) {
        panier.setId(id);
        panierService.createPanier(panier);
        return "redirect:/panier";
    }

    @GetMapping("/deletePanier/{id}")
    public String deletePanier(@PathVariable Long id) {
        panierService.deletePanier(id);
        return "redirect:/panier/list";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProductFromCart(@PathVariable Long productId, HttpSession session) {
        UserSite userSession = (UserSite) session.getAttribute("user");
        UserSite user = userService.getUserById(userSession.getId());
        panierService.removeProductFromCart(productId, user.getPanier().getId());

        return "redirect:/oriPanier";
    }

    /*
     * @GetMapping("/")
     * public String nombreProduitsPanier(Model model) {
     *
     * Utilisateur utilisateurConnecte =
     * utilisateurService.getUtilisateurConnecte();
     * int nombreProduits = 0;
     * if (utilisateurConnecte != null && utilisateurConnecte.getPanier() != null) {
     * nombreProduits = utilisateurConnecte.getPanier().getProduits().size();
     * }
     * int nombreProduits = 0;
     * User user= userService.getUserById(1L);
     * nombreProduits = user.getPanier().getProduits().size();
     * model.addAttribute("nombreProduits", nombreProduits);
     * System.out.println("hhhhhhhhhhhhh"+nombreProduits);
     * return "panier";
     * }
     */

    @PostMapping("/updateTotal")
    public ResponseEntity<?> updateTotal(@RequestParam Long productId, @RequestParam int quantity) {
        // Calculez le nouveau total en fonction de l'ID du produit et de la quantité
        // double newTotal = cartService.calculateNewTotal(productId, quantity);
        System.out.print("paanier");
        double newTotal = quantity * 3;
        // Retournez le nouveau total en tant que réponse JSON
        Map<String, Object> response = new HashMap<>();
        response.put("totalAmount", newTotal);
        return ResponseEntity.ok(response);
    }

}