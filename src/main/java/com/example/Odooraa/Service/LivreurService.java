package com.example.Odooraa.Service;


import com.example.Odooraa.Repository.LivreurRepository;
import com.example.Odooraa.entities.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreurService {
    private final LivreurRepository livreurRepository;

    @Autowired
    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    public Livreur getLivreurById(Long id) {
        return livreurRepository.findById(id).orElse(null);
    }

    public Livreur createLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    public Livreur updateLivreur(Long id, Livreur livreurDetails) {
        Livreur livreur = livreurRepository.findById(id).orElse(null);
        if (livreur != null) {
            // Update livreur details
            return livreurRepository.save(livreur);
        }
        return null;
    }

    public void deleteLivreur(Long id) {
        livreurRepository.deleteById(id);
    }
}
