package com.example.Odooraa.Repository;

import com.example.Odooraa.entities.Panier;
import com.example.Odooraa.entities.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {

}
