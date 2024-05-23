package com.example.Odooraa.Repository;

import com.example.Odooraa.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByQuantityProduct(int quantity);
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.quantityProduct = 0")
    long countByQuantityProductZero();
}
