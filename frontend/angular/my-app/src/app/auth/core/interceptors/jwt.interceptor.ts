import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';

/**
 * Le backend actuel ne génère pas de JWT.
 * L'intercepteur reste en place pour la gestion des erreurs 401
 * et pourra injecter un token dès qu'une couche Spring Security sera ajoutée.
 */
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Pas de token JWT côté backend pour l'instant — on laisse passer la requête telle quelle.
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401) {
          this.authService.logout();
        }
        return throwError(() => err);
      })
    );
  }
}
