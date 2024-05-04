package com.example.Odooraa.Service;


import com.example.Odooraa.Repository.CommandeRepository;
import com.example.Odooraa.entities.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long id, Commande commandeDetails) {
        Commande commande = commandeRepository.findById(id).orElse(null);
        if (commande != null) {
            commande.setDate(commandeDetails.getDate());
            // Set other fields as needed
            return commandeRepository.save(commande);
        }
        return null;
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
