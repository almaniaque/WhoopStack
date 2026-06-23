package com.whoopstack.devis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whoopstack.devis.model.Client;
import com.whoopstack.devis.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAllClient() {
        return repository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return repository.findById(id);
    }

    public Client createClient(Client client) {
        return repository.save(client);

    }

    public boolean deleteClient(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
