import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatMenuModule,
    MatDividerModule,
    RouterOutlet,
    MatTooltipModule,
    RouterLink
  ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar implements OnInit{
  unidadeAtual: any;

  menuItens = [
    {
      titulo: 'Administração',
      icone: 'account_balance',
      tooltip: 'Criação de nova igreja mariz ou filial',
      submenus: [
        {
          label: 'Matriz', 
          rota: '/matriz', 
          icone: 'domain',
          contexto: 'matriz'
        },
        {
          label: 'Filiais', 
          rota: '/filial', 
          icone: 'store',
          contexto: 'filial'
        },
        {
          label: 'Departamentos', 
          rota: '/', 
          icone: 'store',
          contexto: 'filial'
        },
        {
          label: 'Relatórios', 
          rota: '/', 
          icone: 'dashboard',
          contexto: 'filial'
        }
      ],

    },
    {
      titulo: 'Secretaria',
      icone: 'people',
      tooltip: 'Criação ou consulta de eventos em geral',
      submenus: [
        {
          label: 'Membros', 
          rota: '/membro/cadastro', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Batismo', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Conversões', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Visitantes', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Células', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Grupos', 
          rota: '', 
          icone: '',
          contexto: ''
        }
      ]
    },
    {
      titulo: 'Ministérios',
      icone: 'groups',
      tooltip: '',
      submenus: [
        {
          label: 'Louvor', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Jovens', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Adolescentes', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Infantil', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Homens', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Mulheres', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Dança', 
          rota: '', 
          icone: '',
          contexto: ''
        },
      ]
    },
    {
      titulo: 'Atividades',
      icone: 'event_available',
      tooltip: 'Criação ou consulta de membros',
      submenus: [
        {
          label: 'Cultos', 
          rota: '', 
          icone: 'add_circle',
          contexto: ''
        },
        {
          label: 'Eventos', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Escalas', 
          rota: '', 
          icone: '',
          contexto: ''
        }
      ]
    },
    {
      titulo: 'Financeiro',
      icone: 'paid',
      tooltip: 'Criação ou consulta de membros',
      submenus: [
        {
          label: 'Dízimos', 
          rota: '', 
          icone: 'add_circle',
          contexto: ''
        },
        {
          label: 'Ofertas', 
          rota: '', 
          icone: '',
          contexto: ''
        },
        {
          label: 'Despesas', 
          rota: '', 
          icone: '',
          contexto: ''
        }
      ]
    }
  ]

  ngOnInit(): void {
    this.unidadeAtual = {
      nome: 'Filial São Paulo',
      identificador: 'SP-01'
    };
  }

  logout(){
  }
}
