import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard';


export const routes: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent
    },

    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },

    {
        path: 'accueil',
        loadComponent: () => import('./accueil/accueil').then(m => m.Accueil)
    },

    {
        path: 'parametres',
        loadComponent: () => import('./parametres/parametres').then(m => m.Parametres)
    }

];
