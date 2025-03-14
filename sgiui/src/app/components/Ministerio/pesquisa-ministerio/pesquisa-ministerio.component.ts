import { Component } from '@angular/core';
import { ConsultaGeralComponent } from "../../Core/consulta-geral/consulta-geral.component";
import { Ministerio } from '../../../models/models';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-pesquisa-ministerio',
  imports: [ConsultaGeralComponent, RouterLink],
  templateUrl: './pesquisa-ministerio.component.html',
  styleUrl: './pesquisa-ministerio.component.css'
})
export class PesquisaMinisterioComponent {

  ministerio!: Ministerio;

  constructor(){
    this.ministerio = new Ministerio();
  }
}
