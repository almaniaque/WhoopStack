package com.whoopstack.devis.userAuth.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Table
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String email;
    private String name;
    private String nbSiret;
    private String adresse;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDate lastLoginAt;
    private String activate;

    public AppUser() {

    }
}
