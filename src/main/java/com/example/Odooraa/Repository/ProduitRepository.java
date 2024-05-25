package com.example.Odooraa.Repository;

import com.example.Odooraa.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByQuantityProduct(int quantity);
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.quantityProduct = 0")
    long countByQuantityProductZero();

    @Query(value = "SELECT p FROM Produit p " +
            "JOIN p.paniers panier " +
            "GROUP BY p " +
            "ORDER BY COUNT(panier) DESC")
    List<Produit> findTopBestSellingProducts(int limit);

    @Query("SELECT p FROM Produit p WHERE p.categorie = :categorie")
    List<Produit> findByCategory(@Param("categorie") String categorie);
}
