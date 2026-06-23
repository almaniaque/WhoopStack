package com.whoopstack.devis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whoopstack.devis.model.Devis;
import com.whoopstack.devis.ressource.DashboardStatsDto;

@Service
public class DashboardService {
    private final ClientService clientService;
    private final DevisService devisService;

    public DashboardService(ClientService clientService, DevisService devisService) {
        this.clientService = clientService;
        this.devisService = devisService;
    }

    public DashboardStatsDto getStats() {
        int totalClients = clientService.getAllClient().size();

        List<Devis> devisList = devisService.getAllDevis();
        int totalDevis = devisList.size();

        int devisBrouillon = 0;
        int devisEnAttente = 0;
        int devisEnCours = 0;
        int devisRefuse = 0;
        int devisAnnule = 0;
        int devisAcceptes = 0;

        double montantTotal = 0.0;
        double montantAccepte = 0.0;
        double montantPotentiel = 0.0;

        for (Devis devis : devisList) {

            if (devis.getMontantTotal() != null) {
                montantTotal = montantTotal + devis.getMontantTotal();
            }
            String statut = devis.getStatut();

            if (statut == null) {
                continue;
            }

            if (statut.equals("BROUILLON")) {
                devisBrouillon++;
            }

            if (statut.equals("REFUSE")) {
                devisRefuse++;
            }

            if (statut.equals("ANNULE")) {
                devisAnnule++;
            }
            if (statut.equals("EN_ATTENTE")) {
                devisEnAttente++;

                if (devis.getMontantTotal() != null) {
                    montantPotentiel = montantPotentiel + devis.getMontantTotal();
                }
            }

            if (statut.equals("ACCEPTE")) {
                devisAcceptes++;

                if (devis.getMontantTotal() != null) {
                    montantAccepte = montantAccepte + devis.getMontantTotal();
                    montantPotentiel = montantPotentiel + devis.getMontantTotal();
                }
            }
            if (statut.equals("EN_COURS")) {
                devisEnCours++;

                if (devis.getMontantTotal() != null) {
                    montantAccepte = montantAccepte + devis.getMontantTotal();
                    montantPotentiel = montantPotentiel + devis.getMontantTotal();
                }
            }

        }

        return new DashboardStatsDto(
                totalClients,
                totalDevis,
                devisBrouillon,
                devisEnAttente,
                devisEnCours,
                devisRefuse,
                devisAnnule,
                devisAcceptes,
                montantTotal,
                montantAccepte,
                montantPotentiel);
    }
}
