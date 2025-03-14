import { Component, Input, OnInit } from '@angular/core';
import { Ministerio } from '../../../models/models';

@Component({
  selector: 'app-consulta-geral',
  imports: [],
  templateUrl: './consulta-geral.component.html',
  styleUrl: './consulta-geral.component.css'
})
export class ConsultaGeralComponent implements OnInit{
  @Input() entrada: any;
  @Input() titulo!: string;
  camposTabela!: string[];

  constructor(){

  }

  ngOnInit(): void {
    this.camposTabela = Object.keys(this.entrada);
  }

}
