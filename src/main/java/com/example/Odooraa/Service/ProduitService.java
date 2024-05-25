package com.example.Odooraa.Service;


import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit != null) {
            // Update produit details
            return produitRepository.save(produit);
        }
        return null;
    }

    public List<Produit> getTopBestSellingProducts(int limit) {
        return produitRepository.findTopBestSellingProducts(limit);
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
