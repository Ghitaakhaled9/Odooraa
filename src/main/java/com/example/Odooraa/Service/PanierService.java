package com.example.Odooraa.Service;

import com.example.Odooraa.Repository.PanierRepository;
import com.example.Odooraa.Repository.ProduitRepository;
import com.example.Odooraa.entities.Panier;
import com.example.Odooraa.entities.Produit;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PanierService {
    private final PanierRepository panierRepository;
    private final ProduitRepository produitRepository;

    @Autowired
    public PanierService(PanierRepository panierRepository, ProduitRepository produitRepository) {
        this.panierRepository = panierRepository;
        this.produitRepository = produitRepository;
    }

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Panier getPanierById(Long id) {
        return panierRepository.findById(id).orElse(null);
    }

    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public Panier updatePanier(Long id, Panier panierDetails) {
        Panier panier = panierRepository.findById(id).orElse(null);
        if (panier != null) {
            // Update panier details
            return panierRepository.save(panier);
        }
        return null;
    }

    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }

    public void removeProductFromCart(Long productId, Long cartId) {
        // Rechercher le panier par son ID
        Panier panier = panierRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Panier not found"));

        // Rechercher le produit par son ID
        Produit produit = produitRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produit not found"));

        // Vérifier si le produit est présent dans le panier
        if (!panier.getProduits().contains(produit)) {
            throw new IllegalStateException("Produit not in cart");
        }

        // Supprimer le produit du panier
        panier.getProduits().remove(produit);
        panierRepository.save(panier);
    }

    public void ajouterProduitAuPanier(Long panierId, Produit produit) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        panier.getProduits().add(produit);

        panierRepository.save(panier);
    }
    public void ajouterProduitAuPanierLongPram(Long panierId, Long produitid) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = produitRepository.findById(produitid).orElseThrow(() -> new RuntimeException("Produit non trouvé"));;


        panier.getProduits().add(produit);

        panierRepository.save(panier);
    }

}
