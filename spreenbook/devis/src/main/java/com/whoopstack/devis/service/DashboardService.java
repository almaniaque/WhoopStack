package com.whoopstack.devis.service;

import java.util.List;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

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
        int devisRefuses = 0;
        int devisAnnules = 0;
        int devisAcceptes = 0;

        double montantTotal = 0.0;
        double montantAccepte = 0.0;
        double montantPotentiel = 0.0;

        List<String> moisLabels = new ArrayList<>();
        List<Double> caParMois = new ArrayList<>();
        List<Integer> devisParMois = new ArrayList<>();
        YearMonth moisActuel = YearMonth.now();

        for (int i = 5; i >= 0; i--) {
            YearMonth mois = moisActuel.minusMonths(i);

            String label = mois.getMonth()
                    .getDisplayName(TextStyle.SHORT, Locale.FRANCE);

            moisLabels.add(label);

            double caMois = 0.0;
            int devisMois = 0;

            for (Devis devis : devisList) {
                LocalDate dateCreation = devis.getDateCreation();

                if (dateCreation == null) {
                    continue;
                }

                YearMonth moisDuDevis = YearMonth.from(dateCreation);

                if (!moisDuDevis.equals(mois)) {
                    continue;
                }

                devisMois++;

                String statut = devis.getStatut();
                Double montant = devis.getMontantTotal();

                if ("ACCEPTE".equals(statut) && montant != null) {
                    caMois += montant;
                }
            }

            caParMois.add(caMois);
            devisParMois.add(devisMois);
        }

        for (Devis devis : devisList) {
            Double montant = devis.getMontantTotal();

            if (montant != null) {
                montantTotal += montant;
            }

            String statut = devis.getStatut();

            if (statut == null) {
                continue;
            }

            switch (statut) {
                case "BROUILLON" -> devisBrouillon++;

                case "EN_ATTENTE" -> {
                    devisEnAttente++;

                    if (montant != null) {
                        montantPotentiel += montant;
                    }
                }

                case "EN_COURS" -> {
                    devisEnCours++;

                    if (montant != null) {
                        montantPotentiel += montant;
                    }
                }

                case "REFUSE" -> devisRefuses++;

                case "ANNULE" -> devisAnnules++;

                case "ACCEPTE" -> {
                    devisAcceptes++;

                    if (montant != null) {
                        montantAccepte += montant;
                    }
                }

                default -> {

                }
            }
        }

        int devisEmis = totalDevis;

        double chiffreAffaires = montantAccepte;

        double tauxConversion = 0.0;
        if (totalDevis > 0) {
            tauxConversion = ((double) devisAcceptes / totalDevis) * 100;
            tauxConversion = Math.round(tauxConversion * 100.0) / 100.0;
        }

        double delaiMoyenReponse = 0.0;
        double evolutionChiffreAffaires = 0.0;
        double evolutionDevis = 0.0;
        double evolutionConversion = 0.0;
        double evolutionDelai = 0.0;

        return new DashboardStatsDto(
                totalClients,
                totalDevis,
                devisEmis,
                devisBrouillon,
                devisEnAttente,
                devisEnCours,
                devisRefuses,
                devisAnnules,
                devisAcceptes,
                montantTotal,
                montantAccepte,
                montantPotentiel,
                chiffreAffaires,
                tauxConversion,
                delaiMoyenReponse,
                evolutionChiffreAffaires,
                evolutionDevis,
                evolutionConversion,
                evolutionDelai,
                moisLabels,
                caParMois,
                devisParMois);
    }
}