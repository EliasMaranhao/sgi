import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private readonly API = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient){}

  login(dados: any) {
    return this.http.post<any>(this.API, dados).pipe(
      tap(res => {
        console.log('Token guardado no localStorage');
        localStorage.setItem('token', res.token);
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(token: string | null): boolean {
    if (!token) return false;

    try {
      const decoded: any = jwtDecode(token);
      const currentTime = Math.floor(Date.now() / 1000);

      // Verifica se a propriedade 'exp' existe e se é maior que o tempo atual
      return decoded.exp > currentTime;
    } catch (error) {
      return false; // Token malformado ou inválido
    }
    return !!token; 
  }

  logout() {
    localStorage.removeItem('token');
  }
}
