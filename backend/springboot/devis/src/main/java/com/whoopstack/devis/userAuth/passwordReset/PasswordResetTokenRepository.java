package com.whoopstack.devis.userAuth.passwordReset;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findById(String tokenHash);

    Optional<PasswordResetToken> findByTokenHash(String tokenHash);
}
