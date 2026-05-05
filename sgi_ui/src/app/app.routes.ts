import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { MembroCadastro } from './membro/membro-cadastro/membro-cadastro';
import { IgrejaCadastro } from './igreja/igreja-cadastro/igreja-cadastro';
import { MatrizCadastro } from './matriz/matriz-cadastro/matriz-cadastro';
import { authGuard } from './auth/login-guard';
import { MatrizConsulta } from './matriz/matriz-consulta/matriz-consulta';
import { IgrejaConsulta } from './igreja/igreja-consulta/igreja-consulta';

export const routes: Routes = [
  {
    path: 'login', 
    component: Login
  },
  {
    path: 'filial',
    component: IgrejaCadastro,
    canActivate: [authGuard]
  },
  {
    path: 'filial/cadastro',
    component: IgrejaCadastro,
    canActivate: [authGuard]
  },
  {
    path: 'filial/editar/:id',
    component: IgrejaCadastro,
    canActivate: [authGuard]
  },
  {
    path: 'matriz',
    component: MatrizConsulta,
    canActivate: [authGuard],
  },
  {
    path: 'matriz/cadastro',
    component: MatrizCadastro,
    canActivate: [authGuard]
  },
  {
    path: 'matriz/editar/:id',
    component: MatrizCadastro,
    canActivate: [authGuard]
  },
  { 
    path: 'membro/cadastro', 
    component: MembroCadastro, 
    canActivate: [authGuard] // Proteção aplicada aqui!
  },
  { 
    path: '', 
    redirectTo: '/login', 
    pathMatch: 'full' 
  }
];
