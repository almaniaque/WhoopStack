import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { DevisFormComponent } from './devis-form/devis-form';

interface Devis {
  id: number;
  client: Client;
  categorie: string;
  montant: number;
  date: string;
  echeance: string;
  statut: string;

}

interface Client {
  id: number;
  name: string;
  email: string;
  telephone: string;
  entreprise: string;
}


@Component({
  selector: 'app-menu-devis',
  imports: [CommonModule, MatTableModule, MatPaginatorModule, FormsModule, DevisFormComponent],
  templateUrl: './menu-devis.html',
  styleUrl: './menu-devis.css',
})
export class MenuDevis implements OnInit {

  devisSelectionne: any = null;
  devisAModifier: any = null;


  dataSource: Devis[] = [];
  pagedData: Devis[] = [];
  pageSize = 8;
  currentPage = 0;
  searchTerm: string = '';
  allData: Devis[] = [];


  displayedColumns: string[] = ['id', 'client', 'categorie', 'montant', 'date', 'echeance', 'statut', 'devis'];

  trackBy = (index: number, item: Devis) => item.id;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Devis[]>('http://localhost:8080/devis').subscribe({
      next: (data) => {
        this.allData = data;
        this.dataSource = data;
        this.updatePage();
      },
      error: (err) => console.error('Erreur chargement devis', err)
    });
  }
  updatePage(): void {
    const start = this.currentPage * this.pageSize;
    this.pagedData = this.dataSource.slice(start, start + this.pageSize);
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePage();
  }

  canEdit(statut: string): boolean {
    return statut === 'Refusé' || statut === 'En_attente';
  }

  getStatutIcon(statut: string): string {
    switch (statut) {
      case 'Accepté': return 'fa-regular fa-circle-check';
      case 'Refusé': return 'fa-regular fa-circle-xmark';
      case 'En_attente': return 'fa-regular fa-clock';
      case 'En_cours': return 'fa-regular fa-clock';
      case 'Annulé': return 'fa-regular fa-ban';
      default: return '';
    }
  }

  formatStatut(statut: string): string {
    return statut.replace('_', ' ');
  }

  onSearch(): void {
    const term = this.searchTerm.toLowerCase();
    this.dataSource = this.allData.filter(d =>
      d.client?.name?.toLowerCase().includes(term) ||
      d.categorie?.toLowerCase().includes(term) ||
      d.statut?.toLowerCase().includes(term)
    );
    this.currentPage = 0;
    this.updatePage();
  }


  filtreActif: string = 'Tous';

  setFiltre(filtre: string): void {
    this.filtreActif = filtre;
    if (filtre === 'Tous') {
      this.dataSource = this.allData;
    } else {
      this.dataSource = this.allData.filter(d => d.statut === filtre);
    }
    this.currentPage = 0;
    this.updatePage();
  }


  voirDevis(devis: any): void {
    this.devisSelectionne = devis;

  }

  modifierDevis(devis: any): void {
    this.devisAModifier = devis;


  }
}