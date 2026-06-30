import { Component, inject, OnInit, PLATFORM_ID } from '@angular/core';
import { DashboardService } from '../../dashboard/services/dashboard';
import { DashboardStats } from '../../dashboard/models/dashboard-stats';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-stat-card',
  imports: [],
  templateUrl: './stat-card.html',
  styleUrl: './stat-card.css',
})

export class StatCard implements OnInit {

  private dashboardService = inject(DashboardService);
  private platformId = inject(PLATFORM_ID);

  stats?: DashboardStats;
  loading = true;
  errorMessage = '';

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadStats();
      console.log('ok navigateur');
    }
  }

  loadStats(): void {
    this.loading = true;
    this.errorMessage = '';

    this.dashboardService.getStats().subscribe({
      next: (data) => {
        this.stats = data;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les données du dashboard.';
        this.loading = false;
      }
    });
  }
}
