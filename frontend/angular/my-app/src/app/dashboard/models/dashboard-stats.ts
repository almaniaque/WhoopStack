export interface DevisResume {
    id: number;
    clientNom: string;
    date: string;
    montant: number;
    statut: string;
}

export interface DashboardStats {
    totalClients: number;
    totalDevis: number;

    devisEmis: number;
    devisBrouillon: number;
    devisEnAttente: number;
    devisEnCours: number;
    devisRefuses: number;
    devisAnnules: number;
    devisAcceptes: number;

    montantCA: number;
    montantAccepte: number;
    montantPotentiel: number;

    chiffreAffaires: number;
    tauxConversion: number;
    delaiMoyenReponse: number;

    evolutionChiffreAffaires: number;
    evolutionDevis: number;
    evolutionConversion: number;
    evolutionDelai: number;

    moisLabels: string[];
    caParMois: number[];
    devisParMois: number[];
    chiffreAffairesMois: number;

    derniersDevis: DevisResume[];
}
