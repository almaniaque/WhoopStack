
package com.whoopstack.devis.userAuth.auth;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whoopstack.devis.userAuth.auth.dto.AuthResponse;
import com.whoopstack.devis.userAuth.auth.dto.LoginRequest;
import com.whoopstack.devis.userAuth.auth.dto.RegisterRequest;
import com.whoopstack.devis.userAuth.auth.dto.UpdatePasswordRequest;
import com.whoopstack.devis.userAuth.user.AppUser;
import com.whoopstack.devis.userAuth.user.AppUserRepository;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (appUserRepository.existsByEmail(email)) {
            throw new RuntimeException("Cet email existe déja");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setCreatedAt(LocalDateTime.now());

        AppUser savedUser = appUserRepository.save(user);

        return new AuthResponse(
                true,
                "Compte créé avec succès",
                savedUser.getId(),
                savedUser.getEmail());

    }

    public AuthResponse login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        return new AuthResponse(
                true,
                "Connexion Réussie",
                user.getId(),
                user.getEmail());
    }

    public AuthResponse deleteUser(Long id) {

        if (!appUserRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        appUserRepository.deleteById(id);

        return new AuthResponse(
                true,
                "Utilisateur supprimé avec succès",
                id,
                null);
    }

    public AuthResponse updateUserEmail(Long id, String email) {

        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String cleanEmail = email.trim().toLowerCase();

        user.setEmail(cleanEmail);

        AppUser savedUser = appUserRepository.save(user);

        return new AuthResponse(
                true,
                "Email modifié avec succès",
                savedUser.getId(),
                savedUser.getEmail());
    }

    public AuthResponse updateNewPassword(Long id, UpdatePasswordRequest request) {

        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(request.getOldMdp(), user.getPassword())) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }
        System.err.println("ok");
        String hashedNewPassword = passwordEncoder.encode(request.getNewMdp());

        user.setPassword(hashedNewPassword);

        AppUser savedUser = appUserRepository.save(user);

        return new AuthResponse(
                true,
                "Mot de passe changé avec succès",
                savedUser.getId(),
                savedUser.getEmail());
    }
}
