import { Component, inject, Injectable, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard';
import { DashboardStats } from '../models/dashboard-stats';
import { BaseChartDirective } from 'ng2-charts';
import {
  Chart,
  LineElement, PointElement, LineController,
  BarElement, BarController,
  DoughnutController, ArcElement,
  CategoryScale, LinearScale,
  Filler, Tooltip, Legend,
  ChartConfiguration, ChartType
} from 'chart.js';



Chart.register(
  LineElement, PointElement, LineController,
  BarElement, BarController,
  DoughnutController, ArcElement,
  CategoryScale, LinearScale,
  Filler, Tooltip, Legend

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

  ngOnInit(): void {
    this.loadStats();
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

  // ─── Line Chart ───────────────────────────────────────────────
  lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin'],
    datasets: [
      {
        data: [1200, 1800, 1500, 2200, 1900, 2600],
        label: 'CA (€)',
        fill: true,
        tension: 0.4,
        borderColor: '#6c5ce7',
        backgroundColor: 'rgba(108, 92, 231, 0.1)',
        pointBackgroundColor: '#6c5ce7',
      }
    ]
  };

  lineChartOptions: ChartConfiguration<'line'>['options'] = {
    responsive: true,
    plugins: {
      legend: { display: false },
      datalabels: { display: false }
    },
    scales: {
      y: { beginAtZero: true }
    }
  };

  // ─── Bar Chart ────────────────────────────────────────────────
  barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin'],
    datasets: [
      {
        data: [4, 6, 5, 8, 7, 9],
        label: 'Devis émis',
        backgroundColor: 'rgba(108, 92, 231, 0.7)',
        borderColor: '#6c5ce7',
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
      datalabels: { display: false }
    },
    scales: {
      y: { beginAtZero: true, ticks: { stepSize: 1 } }
    }
  };

  // ─── Doughnut Chart ───────────────────────────────────────────
  doughnutChartData: ChartConfiguration<'doughnut'>['data'] = {
    labels: ['Accepté', 'En attente', 'En cours', 'Annulé', 'Refusé'],
    datasets: [
      {
        data: [12, 5, 3, 2, 1],
        backgroundColor: [
          '#6c5ce7',
          '#fdcb6e',
          '#fd79a8',
          '#d63031',
          '#b2bec3',
        ],
        borderWidth: 0,
        hoverOffset: 8,
      }
    ]
  };

  doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] & { plugins?: any } = {
    responsive: true,
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
      tooltip: { enabled: true },
    }
  };

  doughnutPlugins = [{
    id: 'customDatalabels',
    afterDatasetDraw(chart: any) {
      const { ctx } = chart;
      chart.data.datasets.forEach((dataset: any, i: number) => {
        const meta = chart.getDatasetMeta(i);
        meta.data.forEach((element: any, index: number) => {
          const value = dataset.data[index];
          if (value <= 0) return;

          const { x, y } = element.tooltipPosition();
          ctx.save();
          ctx.fillStyle = '#ffffff';
          ctx.font = 'bold 12px sans-serif';
          ctx.textAlign = 'center';
          ctx.textBaseline = 'middle';
          ctx.fillText(value.toString(), x, y);
          ctx.restore();
        });
      });
    }
  }];

  get totalDevis(): number {
    return (this.doughnutChartData.datasets[0].data as number[])
      .reduce((a, b) => a + b, 0);
  }
}