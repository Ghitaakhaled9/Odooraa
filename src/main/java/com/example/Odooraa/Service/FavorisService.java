package com.example.Odooraa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Odooraa.Repository.FavorisRepository;
import com.example.Odooraa.entities.Favoris;

@Service
public class FavorisService {

    private final FavorisRepository favorisRepository;

    @Autowired
    public FavorisService(FavorisRepository favorisRepository) {
        this.favorisRepository = favorisRepository;
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
}
