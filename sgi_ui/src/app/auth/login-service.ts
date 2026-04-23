import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
        localStorage.setItem('token', res.token);
      })
    );
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    // Aqui você pode adicionar uma lógica para checar se o token expirou
    return !!token; 
  }

  logout() {
    localStorage.removeItem('token');
  }
}
