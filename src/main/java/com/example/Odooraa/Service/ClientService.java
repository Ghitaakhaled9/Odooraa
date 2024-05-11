package com.example.Odooraa.Service;

import com.example.Odooraa.Repository.ClientRepository;
import com.example.Odooraa.entities.UserSite;


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

    public List<UserSite> getAllClients() {
        return clientRepository.findAll();
    }

    public UserSite getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public UserSite createClient(UserSite client) {
        return clientRepository.save(client);
    }

    public UserSite updateClient(Long id, UserSite clientDetails) {
        UserSite client = clientRepository.findById(id).orElse(null);
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
