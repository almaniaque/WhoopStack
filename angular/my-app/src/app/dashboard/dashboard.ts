import { Component, inject, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard';
import { DashboardStats } from '../models/dashboard-stats';
import { BaseChartDirective } from 'ng2-charts';

import {
  Chart,
  LineElement,
  PointElement,
  LineController,
  BarElement,
  BarController,
  DoughnutController,
  ArcElement,
  CategoryScale,
  LinearScale,
  Filler,
  Tooltip,
  Legend,
  ChartConfiguration,
} from 'chart.js';

Chart.register(
  LineElement,
  PointElement,
  LineController,
  BarElement,
  BarController,
  DoughnutController,
  ArcElement,
  CategoryScale,
  LinearScale,
  Filler,
  Tooltip,
  Legend
);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent implements OnInit {
  private dashboardService = inject(DashboardService);

  stats?: DashboardStats;
  loading = true;
  errorMessage = '';

  private readonly purple = '#6c5ce7';
  private readonly purpleLight = 'rgba(108, 92, 231, 0.12)';
  private readonly warning = '#fdcb6e';
  private readonly pink = '#fd79a8';
  private readonly danger = '#d63031';
  private readonly grey = '#b2bec3';

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats(): void {
    this.loading = true;
    this.errorMessage = '';

    this.dashboardService.getStats().subscribe({
      next: (data) => {
        this.stats = data;
        this.updateCharts(data);
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les données du dashboard.';
        this.loading = false;
      }
    });
  }

  // ─── Line Chart : évolution du CA ─────────────────────────────

  lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'CA (€)',
        fill: true,
        tension: 0.4,
        borderColor: this.purple,
        backgroundColor: this.purpleLight,
        pointBackgroundColor: this.purple,
      }
    ]
  };

  lineChartOptions: ChartConfiguration<'line'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { display: false },
      tooltip: {
        callbacks: {
          label: (context) => `${context.parsed.y} €`
        }
      }
    },
    scales: {
      y: { beginAtZero: true }
    }
  };

  // ─── Bar Chart : devis par mois ───────────────────────────────

  barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Devis émis',
        backgroundColor: this.purple,
        borderColor: this.purple,
        borderRadius: 6,
        borderWidth: 1,
      }
    ]
  };

  barChartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { display: false },
      tooltip: {
        callbacks: {
          label: (context) => `${context.parsed.y} devis`
        }
      }
    },
    scales: {
      y: {
        beginAtZero: true,
        ticks: { stepSize: 1 }
      }
    }
  };

  // ─── Doughnut Chart : répartition des statuts ─────────────────

  doughnutChartData: ChartConfiguration<'doughnut'>['data'] = {
    labels: ['Accepté', 'En attente', 'En cours', 'Annulé', 'Refusé'],
    datasets: [
      {
        data: [0, 0, 0, 0, 0],
        backgroundColor: [
          this.purple,
          this.warning,
          this.pink,
          this.danger,
          this.grey,
        ],
        borderWidth: 0,
        hoverOffset: 8,
      }
    ]
  };

  doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    cutout: '55%',
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          padding: 12,
          usePointStyle: true,
          boxWidth: 8,
          font: { size: 11 }
        }
      },
      tooltip: { enabled: true }
    }
  };

  // Plugin custom typé en `any[]` pour éviter les conflits de types Chart.js
  doughnutPlugins: any[] = [
    {
      id: 'customDatalabels',
      afterDatasetsDraw(chart: any): void {
        const { ctx } = chart;

        chart.data.datasets.forEach((dataset: any, datasetIndex: number) => {
          const meta = chart.getDatasetMeta(datasetIndex);

          meta.data.forEach((element: any, index: number) => {
            const value = dataset.data[index];

            if (typeof value !== 'number' || value <= 0) return;

            const position = element.tooltipPosition(true);

            ctx.save();
            ctx.fillStyle = '#ffffff';
            ctx.font = 'bold 12px sans-serif';
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.fillText(value.toString(), position.x, position.y);
            ctx.restore();
          });
        });
      }
    }
  ];

  updateCharts(stats: DashboardStats): void {
    this.lineChartData = {
      labels: stats.moisLabels,
      datasets: [
        {
          data: stats.caParMois,
          label: 'CA (€)',
          fill: true,
          tension: 0.4,
          borderColor: this.purple,
          backgroundColor: this.purpleLight,
          pointBackgroundColor: this.purple,
        }
      ]
    };

    this.barChartData = {
      labels: stats.moisLabels,
      datasets: [
        {
          data: stats.devisParMois,
          label: 'Devis émis',
          backgroundColor: this.purple,
          borderColor: this.purple,
          borderRadius: 6,
          borderWidth: 1,
        }
      ]
    };

    this.doughnutChartData = {
      labels: ['Accepté', 'En attente', 'En cours', 'Annulé', 'Refusé'],
      datasets: [
        {
          data: [
            stats.devisAcceptes,
            stats.devisEnAttente,
            stats.devisEnCours,
            stats.devisAnnules,
            stats.devisRefuses
          ],
          backgroundColor: [
            this.purple,
            this.warning,
            this.pink,
            this.danger,
            this.grey,
          ],
          borderWidth: 0,
          hoverOffset: 8,
        }
      ]
    };
  }

  get totalDevis(): number {
    return this.stats?.totalDevis ?? 0;
  }
}