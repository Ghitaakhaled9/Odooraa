package com.example.Odooraa.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String categorie;
    private double prix;
    private String description;
    private int quantityProduct;

    @ManyToMany(mappedBy = "produits")
    private List<Panier> paniers;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandes;

    @ManyToMany(mappedBy = "produits")
    private List<Favoris> favoris;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
