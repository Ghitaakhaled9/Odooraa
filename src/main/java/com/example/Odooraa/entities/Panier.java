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
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @OneToOne(mappedBy = "panier")
    private User user;

    @ManyToMany
    @JoinTable(name = "panier_produit", joinColumns = @JoinColumn(name = "panier_id"), inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produits;
}
