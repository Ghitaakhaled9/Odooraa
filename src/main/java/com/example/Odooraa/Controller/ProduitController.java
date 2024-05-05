package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ProduitController {


    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/produits")
    public String afficherProduits(Model model) {
        // Récupérer tous les produits depuis le repository
        var produits = produitRepository.findAll();

        // Ajouter les produits au modèle pour l'afficher dans la vue
        model.addAttribute("produits", produits);

        // Retourne le nom du fichier HTML (sans extension)
        return "produit";
    }


}
