package com.example.Odooraa.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favoris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToMany
    @JoinTable(name = "favoris_produit", joinColumns = @JoinColumn(name = "favoris_id"), inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produits;

    @OneToOne(mappedBy = "favoris")
    private UserSite user;

}
