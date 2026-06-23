import { Service } from '@angular/core';
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DashboardStats } from '../models/dashboard-stats';


@Injectable({
    providedIn: 'root'
})
export class DashboardService {
    private http = inject(HttpClient);

    private apiUrl = 'http://localhost:8080/api/dashboard/stats';

    getStats() {
        return this.http.get<DashboardStats>(this.apiUrl);
    }
}