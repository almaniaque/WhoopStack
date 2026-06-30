package com.whoopstack.devis.service;

import java.util.List;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.whoopstack.devis.model.Devis;
import com.whoopstack.devis.ressource.DashboardStatsDto;
import com.whoopstack.devis.ressource.DevisResumeDto;

@Service
public class DashboardService {

    private final ClientService clientService;
    private final DevisService devisService;

    public DashboardService(ClientService clientService, DevisService devisService) {
        this.clientService = clientService;
        this.devisService = devisService;
    }

    public DashboardStatsDto getStatsByUserId(Long userId) {
        int totalClients = clientService.getClientsByUserId(userId).size();

        List<Devis> devisList = devisService.getAllDevis(userId);
        int totalDevis = devisList.size();

        int devisBrouillon = 0;
        int devisEnAttente = 0;
        int devisEnCours = 0;
        int devisRefuses = 0;
        int devisAnnules = 0;
        int devisAcceptes = 0;

        double montantCA = 0.0;
        double montantAccepte = 0.0;
        double montantPotentiel = 0.0;
        double chiffreAffairesMois = 0.0;
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
                LocalDate dateCreation = devis.getDate();

                if (dateCreation == null) {
                    continue;
                }

                YearMonth moisDuDevis = YearMonth.from(dateCreation);

                if (!moisDuDevis.equals(mois)) {
                    continue;
                }

                devisMois++;

                String statut = devis.getStatut();
                int montantTotal = devis.getMontant();

                if ("ACCEPTE".equals(statut) && montantTotal != 0) {
                    caMois += montantTotal;
                }
            }

            caParMois.add(caMois);
            devisParMois.add(devisMois);
        }

        if (!caParMois.isEmpty()) {
            chiffreAffairesMois = caParMois.get(caParMois.size() - 1);
        }

        for (Devis devis : devisList) {
            double montantTotal = devis.getMontant();

            // Total de tous les devis émis
            if (montantTotal != 0) {
                montantCA += montantTotal;
            }

            String statut = devis.getStatut();

            if (statut == null) {
                continue;
            }

            statut = statut.trim().toUpperCase(Locale.ROOT);

            switch (statut) {
                case "BROUILLON" -> devisBrouillon++;

                case "EN_ATTENTE" -> {
                    devisEnAttente++;
                    montantPotentiel += montantTotal;
                }

                case "EN_COURS" -> {
                    devisEnCours++;
                    montantPotentiel += montantTotal;
                }

                case "REFUSE" -> devisRefuses++;

                case "ANNULE" -> devisAnnules++;

                case "ACCEPTE" -> {
                    devisAcceptes++;
                    montantAccepte += montantTotal;
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

        // ── 10 derniers devis triés par date décroissante ─────────
        List<DevisResumeDto> derniersDevis = devisList.stream()
                .filter(d -> d.getDate() != null)
                .sorted((d1, d2) -> d2.getDate().compareTo(d1.getDate()))
                .limit(10)
                .map(d -> new DevisResumeDto(
                        d.getId(),
                        d.getClient() != null ? d.getClient().getName() : "—",
                        d.getDate(),
                        d.getMontant(),
                        d.getStatut()))
                .collect(Collectors.toList());

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
                montantCA,
                montantAccepte,
                montantPotentiel,
                chiffreAffaires,
                chiffreAffairesMois,
                tauxConversion,
                delaiMoyenReponse,
                evolutionChiffreAffaires,
                evolutionDevis,
                evolutionConversion,
                evolutionDelai,
                moisLabels,
                caParMois,
                devisParMois,
                derniersDevis);
    }
}
