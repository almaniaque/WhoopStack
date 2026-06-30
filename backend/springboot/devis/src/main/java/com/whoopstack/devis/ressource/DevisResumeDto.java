package com.whoopstack.devis.ressource;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DevisResumeDto {

    private Long id;
    private String clientNom;

    @JsonFormat(pattern = "dd MMMM yyyy", locale = "fr")
    private LocalDate date;

    private int montant;
    private String statut;

    public DevisResumeDto() {}

    public DevisResumeDto(Long id, String clientNom, LocalDate date, int montant, String statut) {
        this.id = id;
        this.clientNom = clientNom;
        this.date = date;
        this.montant = montant;
        this.statut = statut;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientNom() { return clientNom; }
    public void setClientNom(String clientNom) { this.clientNom = clientNom; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getMontant() { return montant; }
    public void setMontant(int montant) { this.montant = montant; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
