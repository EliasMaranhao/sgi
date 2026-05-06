import { Component, inject } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterOutlet, Router, RouterLinkWithHref } from '@angular/router';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatMenuModule } from '@angular/material/menu';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from "@angular/material/form-field";

@Component({
  selector: 'app-barra-ferramentas',
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    RouterOutlet,
    MatTooltipModule,
    MatMenuModule,
    MatExpansionModule,
    MatFormFieldModule,
    RouterLinkWithHref
],
  templateUrl: './barra-ferramentas.html',
  styleUrl: './barra-ferramentas.scss',
})
export class BarraFerramentas {

  router = inject(Router);
  isExpanded: boolean = false;

  menuItens = [
    {
      titulo: 'Igreja',
      icone: 'church',
      tooltip: 'Criação de nova igreja mariz ou filial',
      submenus: [
        {
          label: 'Matriz', 
          rota: '/matriz', 
          icone: 'domain',
          contexto: 'matriz'
        },
        {
          label: 'Filial', 
          rota: '/filial', 
          icone: 'store',
          contexto: 'filial'
        }
      ],

    },
    {
      titulo: 'Eventos',
      icone: 'event',
      tooltip: 'Criação ou consulta de eventos em geral',
      submenus: [
        {
          label: 'Cultos', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Confraterniazações', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Família', 
          rota: '', 
          icone: '',
          contexto: ''
        }
      ]
    },
    {
      titulo: 'Membro',
      icone: 'person',
      tooltip: 'Criação ou consulta de membros',
      submenus: [
        {
          label: 'Novo', 
          rota: '/membro/cadastro', 
          icone: 'add_circle',
          contexto: ''
        },
        {
          label: 'Consultar', 
          rota: '/membro/consultar', 
          icone: '',
          contexto: ''
        }
      ]
    }
  ]

  toggleMenu() {
    this.isExpanded = !this.isExpanded;
  }
}
