import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from './login-service';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(LoginService);
  const token = authService.getToken();
  const tenantId = localStorage.getItem('tenant_id');

  let newHeaders: any = {};

  if (token) {
      newHeaders['Authorization'] = `Bearer ${token}`;
  }

  if (tenantId) {
      newHeaders['X-Tenant-ID'] = tenantId;
  }

  // 1. Validação local antes de enviar a requisição
  if (token && !authService.isLoggedIn(token)) {
    authService.logout();
    return throwError(() => new Error('Sessão expirada.')); 
  }

  // 2. Definir qual requisição prosseguirá (a original ou a clonada)
  let requestToForward = req;

  if (token && !req.url.includes('/api/login')) {
    console.log('TOKEN ADICIONADO NA URL ' + req.url);
    requestToForward = req.clone({
      setHeaders: newHeaders
    });
  }

  return next(requestToForward).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        authService.logout();
      }
      return throwError(() => error);
    })
  );
};

