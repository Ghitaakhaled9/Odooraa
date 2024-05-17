package com.example.Odooraa.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@PrimaryKeyJoinColumn(name = "user_id")
public class UserSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @OneToOne
    @JoinColumn(name = "favoris_id")
    private Favoris favoris;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commande> commandes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Produit> produits;

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("sexe")
    private sexe sexe;
    @JsonProperty("adresse")
    private String adresse;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("email")
    private String email;
    @JsonProperty("type")
    private UserType type;

}
