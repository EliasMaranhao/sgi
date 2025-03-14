import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MenuVerticalMembroComponent } from './membro/menu-vertical-membro/menu-vertical-membro.component';
import { CadastrarMinisterioComponent } from './ministerio/cadastrar-ministerio/cadastrar-ministerio.component';
import { PesquisarMinisterioComponent } from './ministerio/pesquisar-ministerio/pesquisar-ministerio.component';
import { EstruturaComponent } from './core/estrutura/estrutura.component';
import { MenuVerticalMinisterioComponent } from './ministerio/menu-vertical-ministerio/menu-vertical-ministerio.component';
import { LoginComponent } from './usuario/login/login.component';
import { CadastrarMembroComponent } from './membro/cadastrar-membro/cadastrar-membro.component';
import { PesquisarMembroComponent } from './membro/pesquisar-membro/pesquisar-membro.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },

  {
    path: 'ministerio', 
    component: MenuVerticalMinisterioComponent,
    children: [
      {
        path: 'novo',
        component: CadastrarMinisterioComponent,
        outlet: 'conteudo'
      },

      {
        path: 'pesquisar',
        component: PesquisarMinisterioComponent,
        outlet: 'conteudo'
      }
    ]
  },

  {
    path: 'membro', 
    component: MenuVerticalMembroComponent,
    children: [
      {
        path: 'novo',
        component: CadastrarMembroComponent,
        outlet: 'conteudo'
      },

      {
        path: 'pesquisar',
        component: PesquisarMembroComponent,
        outlet: 'conteudo'
      }
    ]
  },

  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
