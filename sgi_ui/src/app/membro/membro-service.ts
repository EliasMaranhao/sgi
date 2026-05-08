import { inject, Injectable } from '@angular/core';
import { Membro } from '../models';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MembroService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api';

  cadastrarMembro(membro: Membro): Observable<Membro>{
    return this.http.post<Membro>(`${this.apiUrl}/membro`, membro);
  }
}
