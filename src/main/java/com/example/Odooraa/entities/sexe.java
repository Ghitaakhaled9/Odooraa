package com.example.Odooraa.entities;

public enum sexe {
    Femme, Homme, Autre, DEFAULT; // Default value

    public static sexe fromString(String value) {
        if (value != null) {
            for (sexe sexe : sexe.values()) {
                if (value.equalsIgnoreCase(sexe.name())) {
                    return sexe;
                }
            }
        }
        return null; // Handle unrecognized values
    }
}
