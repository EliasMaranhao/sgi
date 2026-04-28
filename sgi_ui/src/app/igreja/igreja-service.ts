import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filial, Matriz } from '../models';

@Injectable({
  providedIn: 'root',
})
export class IgrejaService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api';

  cadastrarMatriz(matriz: Matriz): Observable<Matriz> {
    return this.http.post<Matriz>(`${this.apiUrl}/matriz`, matriz);
  }

  excluirMatriz(matriz: Matriz): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/matriz/${matriz.id}`);
  }

  buscarMatrizTodas(){
    return this.http.get<Matriz[]>(`${this.apiUrl}/matriz`);
  }

  buscarMatrizPeloId(id: number): Observable<Matriz> {
    return this.http.get<Matriz>(`${this.apiUrl}/matriz/${id}`)
  }

  atualizarMatriz(id: number, matriz: Matriz): Observable<Matriz> {
    return this.http.put<Matriz>(`${this.apiUrl}/matriz/${id}`, matriz);
  }

  cadastrarFilial(filial: Filial): Observable<Filial>{
    return this.http.post<Filial>(`${this.apiUrl}/filial`, filial);
  }

  buscarFilialPeloId(id: number): Observable<Filial>{
    return this.http.get<Filial>(`${this.apiUrl}/filial/${id}`);
  }

  buscarFiliaisPeloMatrizId(matrizId: number): Observable<Filial[]>{
    return this.http.get<Filial[]>(`${this.apiUrl}/matriz/filiais/${matrizId}`);
  }

  excluirFilial(filial: Filial): Observable<void> {
     return this.http.delete<void>(`${this.apiUrl}/filial/${filial.id}`);
  }

  buscarFilialTodas(): Observable<Filial[]>{
    return this.http.get<Filial[]>(`${this.apiUrl}/filial`);
  }

  atualizarFilial(id: number, filial: Filial): Observable<Filial>{
    return this.http.put<Filial>(`${this.apiUrl}/filial/${id}`, filial);
  }
}
