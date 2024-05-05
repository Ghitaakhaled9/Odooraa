package com.example.Odooraa.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
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
    private String date;

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
