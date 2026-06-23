package com.whoopstack.devis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.whoopstack.devis.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // findAll(), findById(), save(), deleteById() sont déjà hérités !

    Optional<User> findById(Long id);
}
