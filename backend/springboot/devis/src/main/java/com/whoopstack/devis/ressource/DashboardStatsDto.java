package com.whoopstack.devis.ressource;

import java.util.List;

public class DashboardStatsDto {

    private int totalClients;
    private int totalDevis;

    private int devisEmis;
    private int devisBrouillon;
    private int devisEnAttente;
    private int devisEnCours;
    private int devisRefuses;
    private int devisAnnules;
    private int devisAcceptes;

    private double montantTotal;
    private double montantAccepte;
    private double montantPotentiel;

    private double chiffreAffaires;
    private double tauxConversion;
    private double delaiMoyenReponse;

    private double evolutionChiffreAffaires;
    private double evolutionDevis;
    private double evolutionConversion;
    private double evolutionDelai;
    private double chiffreAffairesMois;

    private List<String> moisLabels;
    private List<Double> caParMois;
    private List<Integer> devisParMois;

    private List<DevisResumeDto> derniersDevis;

    public DashboardStatsDto(int totalClients, int totalDevis, int devisEmis, int devisBrouillon, int devisEnAttente,
            int devisEnCours, int devisRefuses, int devisAnnules, int devisAcceptes, double montantTotal,
            double montantAccepte, double montantPotentiel, double chiffreAffaires, double tauxConversion,
            double delaiMoyenReponse, double evolutionChiffreAffaires, double evolutionDevis,
            double evolutionConversion, double evolutionDelai, double chiffreAffairesMois, List<String> moisLabels,
            List<Double> caParMois, List<Integer> devisParMois, List<DevisResumeDto> derniersDevis) {
        this.totalClients = totalClients;
        this.totalDevis = totalDevis;
        this.devisEmis = devisEmis;
        this.devisBrouillon = devisBrouillon;
        this.devisEnAttente = devisEnAttente;
        this.devisEnCours = devisEnCours;
        this.devisRefuses = devisRefuses;
        this.devisAnnules = devisAnnules;
        this.devisAcceptes = devisAcceptes;
        this.montantTotal = montantTotal;
        this.montantAccepte = montantAccepte;
        this.montantPotentiel = montantPotentiel;
        this.chiffreAffaires = chiffreAffaires;
        this.tauxConversion = tauxConversion;
        this.delaiMoyenReponse = delaiMoyenReponse;
        this.evolutionChiffreAffaires = evolutionChiffreAffaires;
        this.evolutionDevis = evolutionDevis;
        this.evolutionConversion = evolutionConversion;
        this.evolutionDelai = evolutionDelai;
        this.chiffreAffairesMois = chiffreAffairesMois;
        this.moisLabels = moisLabels;
        this.caParMois = caParMois;
        this.devisParMois = devisParMois;
        this.derniersDevis = derniersDevis;
    }

    public DashboardStatsDto() {
    }

    // ── Getters / Setters ────────────────────────────────────────

    public int getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }

    public int getTotalDevis() {
        return totalDevis;
    }

    public void setTotalDevis(int totalDevis) {
        this.totalDevis = totalDevis;
    }

    public int getDevisEmis() {
        return devisEmis;
    }

    public void setDevisEmis(int devisEmis) {
        this.devisEmis = devisEmis;
    }

    public int getDevisBrouillon() {
        return devisBrouillon;
    }

    public void setDevisBrouillon(int devisBrouillon) {
        this.devisBrouillon = devisBrouillon;
    }

    public int getDevisEnAttente() {
        return devisEnAttente;
    }

    public void setDevisEnAttente(int devisEnAttente) {
        this.devisEnAttente = devisEnAttente;
    }

    public int getDevisEnCours() {
        return devisEnCours;
    }

    public void setDevisEnCours(int devisEnCours) {
        this.devisEnCours = devisEnCours;
    }

    public int getDevisRefuses() {
        return devisRefuses;
    }

    public void setDevisRefuses(int devisRefuses) {
        this.devisRefuses = devisRefuses;
    }

    public int getDevisAnnules() {
        return devisAnnules;
    }

    public void setDevisAnnules(int devisAnnules) {
        this.devisAnnules = devisAnnules;
    }

    public int getDevisAcceptes() {
        return devisAcceptes;
    }

    public void setDevisAcceptes(int devisAcceptes) {
        this.devisAcceptes = devisAcceptes;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public double getMontantAccepte() {
        return montantAccepte;
    }

    public void setMontantAccepte(double montantAccepte) {
        this.montantAccepte = montantAccepte;
    }

    public double getMontantPotentiel() {
        return montantPotentiel;
    }

    public void setMontantPotentiel(double montantPotentiel) {
        this.montantPotentiel = montantPotentiel;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }

    public double getTauxConversion() {
        return tauxConversion;
    }

    public void setTauxConversion(double tauxConversion) {
        this.tauxConversion = tauxConversion;
    }

    public double getDelaiMoyenReponse() {
        return delaiMoyenReponse;
    }

    public void setDelaiMoyenReponse(double delaiMoyenReponse) {
        this.delaiMoyenReponse = delaiMoyenReponse;
    }

    public double getEvolutionChiffreAffaires() {
        return evolutionChiffreAffaires;
    }

    public void setEvolutionChiffreAffaires(double evolutionChiffreAffaires) {
        this.evolutionChiffreAffaires = evolutionChiffreAffaires;
    }

    public double getEvolutionDevis() {
        return evolutionDevis;
    }

    public void setEvolutionDevis(double evolutionDevis) {
        this.evolutionDevis = evolutionDevis;
    }

    public double getEvolutionConversion() {
        return evolutionConversion;
    }

    public void setEvolutionConversion(double evolutionConversion) {
        this.evolutionConversion = evolutionConversion;
    }

    public double getEvolutionDelai() {
        return evolutionDelai;
    }

    public void setEvolutionDelai(double evolutionDelai) {
        this.evolutionDelai = evolutionDelai;
    }

    public List<String> getMoisLabels() {
        return moisLabels;
    }

    public void setMoisLabels(List<String> moisLabels) {
        this.moisLabels = moisLabels;
    }

    public List<Double> getCaParMois() {
        return caParMois;
    }

    public void setCaParMois(List<Double> caParMois) {
        this.caParMois = caParMois;
    }

    public List<Integer> getDevisParMois() {
        return devisParMois;
    }

    public void setDevisParMois(List<Integer> devisParMois) {
        this.devisParMois = devisParMois;
    }

    public List<DevisResumeDto> getDerniersDevis() {
        return derniersDevis;
    }

    public void setDerniersDevis(List<DevisResumeDto> derniersDevis) {
        this.derniersDevis = derniersDevis;
    }

    public double getChiffreAffairesMois() {
        return chiffreAffairesMois;
    }

    public void setChiffreAffairesMois(double chiffreAffairesMois) {
        this.chiffreAffairesMois = chiffreAffairesMois;
    }
}
