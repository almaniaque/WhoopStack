package com.whoopstack.devis.ressource;

public class DashboardStatsDto {

    private int totalClients;
    private int totalDevis;

    private int devisBrouillon;
    private int devisEnAttente;
    private int devisEnCours;
    private int devisRefuse;
    private int devisAnnule;
    private int devisAcceptes;

    private double montantTotal;
    private double montantAccepte;
    private double montantPotentiel;

    public DashboardStatsDto() {

    }

    public DashboardStatsDto(int totalClients, int totalDevis, int devisBrouillon, int devisEnAttente, int devisEnCours,
            int devisRefuse, int devisAnnule, int devisAcceptes, double montantTotal, double montantAccepte,
            double montantPotentiel) {
        this.totalClients = totalClients;
        this.totalDevis = totalDevis;
        this.devisBrouillon = devisBrouillon;
        this.devisEnAttente = devisEnAttente;
        this.devisEnCours = devisEnCours;
        this.devisRefuse = devisRefuse;
        this.devisAnnule = devisAnnule;
        this.devisAcceptes = devisAcceptes;
        this.montantTotal = montantTotal;
        this.montantAccepte = montantAccepte;
        this.montantPotentiel = montantPotentiel;
    }

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

    public int getDevisRefuse() {
        return devisRefuse;
    }

    public void setDevisRefuse(int devisRefuse) {
        this.devisRefuse = devisRefuse;
    }

    public int getDevisAnnule() {
        return devisAnnule;
    }

    public void setDevisAnnule(int devisAnnule) {
        this.devisAnnule = devisAnnule;
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

}
