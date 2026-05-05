import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login-service';

export const authGuard: CanActivateFn = (_route, state) => {
  const authService = inject(LoginService);
  const router = inject(Router);

  const token = authService.getToken();

  if (token && authService.isLoggedIn(token)) {
    return true;
  }

  router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
  
  return false;
};
