import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from './login-service';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(LoginService);
  const token = authService.getToken();

  // 1. Validação local antes de enviar a requisição
  if (token && !authService.isLoggedIn(token)) {
    authService.logout();
    return throwError(() => new Error('Sessão expirada.')); 
  }

  if (token && !req.url.includes('/api/login')) {
    console.log('TOKEN ADICIONADO');
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        authService.logout();
      }
      return throwError(() => error);
    })
  );
};

