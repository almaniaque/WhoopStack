export interface DashboardStats {
    chiffreAffaires: number;
    devisEmis: number;
    tauxConversion: number;
    delaiMoyenReponse: number;

    evolutionChiffreAffaires: number;
    evolutionDevis: number;
    evolutionConversion: number;
    evolutionDelai: number;

    acceptes: number;
    enAttente: number;
    enCours: number;
    annules: number;
    refuses: number;
}