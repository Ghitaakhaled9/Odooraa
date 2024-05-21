package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.Service.ProduitService;
import com.example.Odooraa.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitService produitService;

    @GetMapping("/produits")
    public String afficherProduits(Model model) {
        // Récupérer tous les produits depuis le repository
        var produits = produitRepository.findAll();

        // Ajouter les produits au modèle pour l'afficher dans la vue
        model.addAttribute("produits", produits);

        // Retourne le nom du fichier HTML (sans extension)
        return "produit";
    }


    @PostMapping("/produits/add")
    public String ajouterProduit(
            @RequestParam("marque") String marque,
            @RequestParam("categorie") String categorie,
            @RequestParam("prix") double prix,
            @RequestParam("description") String description,
            @RequestParam("quantite") int quantite,
            @RequestParam("date1") String dateString
    ) {
        LocalDate date = null;

        // Essayez de convertir la chaîne en LocalDate avec gestion d'erreur
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE); // Utilisez ISO_LOCAL_DATE ou un format personnalisé
        } catch (DateTimeParseException e) {
            // Si la conversion échoue, gérez l'erreur, par exemple, renvoyez un message d'erreur ou définissez une date par défaut
            return "redirect:/produits/error"; // Redirection vers une page d'erreur ou autre traitement
        }

        Produit produit = new Produit();
        produit.setMarque(marque);
        produit.setCategorie(categorie);
        produit.setPrix(prix);
        produit.setDescription(description);
        produit.setQuantityProduct(quantite);

        // Assurez-vous que la date n'est pas nulle avant d'utiliser toString
        if (date != null) {
            produit.setDate(date.toString());
        } else {
            produit.setDate("Date inconnue"); // Valeur par défaut en cas de problème
        }

        produitRepository.save(produit);

        return "redirect:/produits"; // Redirection après l'ajout
    }


    @GetMapping("/produits/delete/{id}")
    public String deleteProduits(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return "redirect:/produits";
    }
    
    @PostMapping("/produits/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute Produit updatedProduct) {
        Produit product = produitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        product.setMarque(updatedProduct.getMarque());
        product.setCategorie(updatedProduct.getCategorie());
        product.setPrix(updatedProduct.getPrix());
        product.setDescription(updatedProduct.getDescription());
        product.setQuantityProduct(updatedProduct.getQuantityProduct());
        product.setDate(updatedProduct.getDate());
        produitRepository.save(product);
        return "redirect:/products";
    }


}
