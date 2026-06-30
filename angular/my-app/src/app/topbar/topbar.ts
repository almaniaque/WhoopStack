import { Component, signal } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialog } from '@angular/material/dialog';
import { Parametres } from '../parametres/parametres';

@Component({
  selector: 'app-topbar',
  imports: [RouterLink, RouterLinkActive, MatMenuModule],
  templateUrl: './topbar.html',
  styleUrl: './topbar.css',
})
export class Topbar {
  isOpen = signal(true);

  constructor(private dialog: MatDialog) { }

  toggle() {
    this.isOpen.set(!this.isOpen());
  }

  close() {
    this.isOpen.set(false);
  }

  ouvrirParametres() {
    const dialogRef = this.dialog.open(Parametres, {
      width: '560px',
      disableClose: false
    });

    dialogRef.afterClosed().subscribe(resultat => {
      if (resultat) {
        console.log('Profil sauvegardé :', resultat.profil);
        console.log('Préférences sauvegardées :', resultat.preferences);
      }
    });
  }
}