package com.whoopstack.devis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.whoopstack.devis.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // findAll(), findById(), save(), deleteById() sont déjà hérités !

    Optional<Client> findById(Long id);
}
