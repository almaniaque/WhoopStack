import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog'; // Ajoute la modal
import { MatTabsModule } from '@angular/material/tabs'; // Gère les différents onglets
import { MatFormFieldModule } from '@angular/material/form-field'; // Gère les champs du formulaire
import { MatInputModule } from '@angular/material/input'; // Gère les champs du formulaire
import { MatButtonModule } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle'; // Gère l'interrupteur on/off
import { MatSelectModule } from '@angular/material/select'; // Gère les listes déroulantes
import { FormsModule } from '@angular/forms'; // Liaison entre champs et données

@Component({
  selector: 'app-parametres',
  imports: [
    MatDialogModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSlideToggleModule,
    MatSelectModule,
    FormsModule
  ],
  templateUrl: './parametres.html',
  styleUrl: './parametres.css',
})
export class Parametres {

  profil = {
    nom: 'Laurent Dumas',
    email: 'laurent.dumas@whoopstack.fr',
    telephone: '+33 6 12 34 56 78',
    role: 'Admin'
  };

  preferences = {
    notifications: true,
    emailsResume: false,
    langue: 'fr',
    theme: 'clair'
  };

  // Permet de sauvegarder

  constructor(private dialogRef: MatDialogRef<Parametres>) { }

  sauvegarder() {
    this.dialogRef.close({
      profil: this.profil,
      preferences: this.preferences
    });
  }
}