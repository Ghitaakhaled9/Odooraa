package com.example.Odooraa.Service;

import com.example.Odooraa.Repository.ClientRepository;
import com.example.Odooraa.entities.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<User> getAllClients() {
        return clientRepository.findAll();
    }

    public User getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public User createClient(User client) {
        return clientRepository.save(client);
    }

    public User updateClient(Long id, User clientDetails) {
        User client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            client.setUsername(clientDetails.getUsername());
            // Set other fields as needed
            return clientRepository.save(client);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
