package com.whoopstack.devis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.whoopstack.devis.model.Devis;

public interface DevisRepository extends JpaRepository<Devis, Long> {
    // findAll(), findById(), save(), deleteById() sont déjà hérités !

    Optional<Devis> findById(Long id);
}
