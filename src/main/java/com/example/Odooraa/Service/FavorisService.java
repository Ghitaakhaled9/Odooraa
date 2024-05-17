package com.example.Odooraa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Odooraa.Repository.FavorisRepository;
import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Favoris;
import com.example.Odooraa.entities.Panier;
import com.example.Odooraa.entities.Produit;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FavorisService {

    private final FavorisRepository favorisRepository;
    private final ProduitRepository produitRepository;

    @Autowired
    public FavorisService(FavorisRepository favorisRepository, ProduitRepository produitRepository) {
        this.favorisRepository = favorisRepository;
        this.produitRepository = produitRepository;
    }

    public List<Favoris> getAllFavoriss() {
        return favorisRepository.findAll();
    }

    public Favoris getFavorisById(Long id) {
        return favorisRepository.findById(id).orElse(null);
    }

    public Favoris createFavoris(Favoris favoris) {
        return favorisRepository.save(favoris);
    }

    public Favoris updateFavoris(Long id, Favoris favorisDetails) {
        Favoris favoris = favorisRepository.findById(id).orElse(null);
        if (favoris != null) {
            // Update panier details
            return favorisRepository.save(favoris);
        }
        return null;
    }

    public void deleteFavoris(Long id) {
        favorisRepository.deleteById(id);
    }

    public void deleteProduitFromFavoris(Long id) {
        favorisRepository.deleteById(id);
    }

    public void removeProductFromCart(Long productId, Long favoristId) {
        // Rechercher le panier par son ID
        Favoris favoris = favorisRepository.findById(favoristId)
                .orElseThrow(() -> new EntityNotFoundException("Favoris not found"));

        // Rechercher le produit par son ID
        Produit produit = produitRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produit not found"));

        // Vérifier si le produit est présent dans le panier
        if (!favoris.getProduits().contains(produit)) {
            throw new IllegalStateException("Produit not in favoris list");
        }

        // Supprimer le produit du panier
        favoris.getProduits().remove(produit);
        favorisRepository.save(favoris);
    }
}
