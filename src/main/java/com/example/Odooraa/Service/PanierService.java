package com.example.Odooraa.Service;


import com.example.Odooraa.Repository.PanierRepository;
import com.example.Odooraa.entities.Panier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {
    private final PanierRepository panierRepository;

    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
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
}
