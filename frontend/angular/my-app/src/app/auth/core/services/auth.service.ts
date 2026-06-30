import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

// ── Requêtes ──────────────────────────────────────────────────────────────────

export interface LoginRequest {
  email: string;
  password: string;
}

/** Le backend n'utilise que email + password (pas de firstName/lastName) */
export interface RegisterRequest {
  email: string;
  password: string;
}

export interface ForgotPasswordRequest {
  email: string;
}

export interface ResetPasswordRequest {
  token: string;
  newPassword: string;
}

/** PUT /api/auth/user/{id}/password → { oldMdp, newMdp } */
export interface UpdatePasswordRequest {
  oldMdp: string;
  newMdp: string;
}

// ── Réponse ───────────────────────────────────────────────────────────────────

/** Réponse unifiée du backend : pas de JWT, mais success + message + userId + email */
export interface AuthResponse {
  success: boolean;
  message: string;
  userId: number | null;
  email: string | null;
}

// ── Service ───────────────────────────────────────────────────────────────────

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly API_URL = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) { }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, request).pipe(
      tap((res) => {
        if (res.success) {
          localStorage.setItem('userId', String(res.userId));
          localStorage.setItem('user', JSON.stringify({ email: res.email, userId: res.userId }));
        }
      })
    );
  }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/register`, request).pipe(
      tap((res) => {
        if (res.success) {
          localStorage.setItem('userId', String(res.userId));
          localStorage.setItem('user', JSON.stringify({ email: res.email, userId: res.userId }));
        }
      })
    );
  }

  forgotPassword(request: ForgotPasswordRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/forgot-password`, request);
  }

  resetPassword(request: ResetPasswordRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/reset-password`, request);
  }

  /**
   * Changement de mot de passe pour un utilisateur connecté.
   * PUT /api/auth/user/{id}/password
   */
  changePassword(request: UpdatePasswordRequest): Observable<AuthResponse> {
    const userId = this.getUserId();
    if (!userId) {
      throw new Error('Utilisateur non connecté');
    }
    return this.http.put<AuthResponse>(
      `${this.API_URL}/user/${userId}/password`,
      request
    );
  }

  logout(): void {
    localStorage.removeItem('userId');
    localStorage.removeItem('user');
    this.router.navigate(['/auth/login']);
  }

  isAuthenticated(): boolean {
    if (typeof window === 'undefined') return false;
    return !!localStorage.getItem('userId');
  }

  getUserId(): string | null {
    if (typeof window === 'undefined') return null;
    return localStorage.getItem('userId');
  }

  /** Conservé pour compatibilité avec l'intercepteur JWT (renvoie null car pas de token) */
  getToken(): string | null {
    return null;
  }
}
