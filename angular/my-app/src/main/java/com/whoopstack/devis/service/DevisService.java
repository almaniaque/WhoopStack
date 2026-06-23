package com.whoopstack.devis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whoopstack.devis.model.Devis;
import com.whoopstack.devis.repository.DevisRepository;

@Service
public class DevisService {
    private final DevisRepository repository;

    public DevisService(DevisRepository repository) {
        this.repository = repository;
    }

    public List<Devis> getAllDevis() {
        return repository.findAll();
    }

    public Optional<Devis> getDevisById(Long id) {
        return repository.findById(id);
    }

    public Devis createDevis(Devis devis) {
        return repository.save(devis);
    }

    public Optional<Devis> updateDevis(Long id, Devis updatedDevis) {
        return repository.findById(id).map(existing -> {
            existing.setTitre(updatedDevis.getTitre());
            existing.setDescription(updatedDevis.getDescription());
            existing.setNomClient(updatedDevis.getNomClient());
            existing.setMontantTotal(updatedDevis.getMontantTotal());
            existing.setStatut(updatedDevis.getStatut());
            return repository.save(existing);
        });
    }

    public boolean deleteDevis(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
