import { Component } from '@angular/core';
import { Membro } from '../../../models/models';
import { ConsultaGeralComponent } from '../../Core/consulta-geral/consulta-geral.component';

@Component({
  selector: 'app-pesquisa-membro',
  imports: [ConsultaGeralComponent],
  templateUrl: './pesquisa-membro.component.html',
  styleUrl: './pesquisa-membro.component.css'
})
export class PesquisaMembroComponent {
  membro!: Membro;

  constructor(){
    this.membro = new Membro();
  }
}
