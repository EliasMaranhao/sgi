import { Component, signal } from '@angular/core';
import { BarraFerramentas } from './core/barra-ferramentas/barra-ferramentas';
import { Navbar } from './core/navbar/navbar';

@Component({
  selector: 'app-root',
  imports: [
    //BarraFerramentas
    Navbar
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('sgi');
}
