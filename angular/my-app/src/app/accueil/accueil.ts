import { Component } from '@angular/core';
import { StatCard } from './stat-card/stat-card';
import { QuickAccessCard } from './quick-access-card/quick-access-card';

@Component({
  selector: 'app-accueil',
  imports: [StatCard, QuickAccessCard],
  templateUrl: './accueil.html',
  styleUrl: './accueil.css',
})
export class Accueil {}
