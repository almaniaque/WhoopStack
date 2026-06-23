package com.whoopstack.devis.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;
    private String nomClient;

    @Column(nullable = false)
    private Double montantTotal;

    private String statut;
    private LocalDate dateCreation;
    private LocalDate dateEcheance;

    public Devis(Long id, String titre, String description, String nomClient, Double montantTotal, String statut,
            LocalDate dateCreation, LocalDate dateEcheance) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nomClient = nomClient;
        this.montantTotal = montantTotal;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
    }

    public Devis() {
        this.dateCreation = LocalDate.now();
        this.statut = "";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

}