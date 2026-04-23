import { Component, signal } from '@angular/core';
import { BarraFerramentas } from './core/barra-ferramentas/barra-ferramentas';
import { Login } from './auth/login/login';

@Component({
  selector: 'app-root',
  imports: [
    BarraFerramentas
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('sgi');
}
