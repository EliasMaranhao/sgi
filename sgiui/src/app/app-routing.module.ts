import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MenuVerticalMembroComponent } from './membro/menu-vertical-membro/menu-vertical-membro.component';
import { CadastrarMinisterioComponent } from './ministerio/cadastrar-ministerio/cadastrar-ministerio.component';
import { PesquisarMinisterioComponent } from './ministerio/pesquisar-ministerio/pesquisar-ministerio.component';
import { EstruturaComponent } from './core/estrutura/estrutura.component';
import { MenuVerticalMinisterioComponent } from './ministerio/menu-vertical-ministerio/menu-vertical-ministerio.component';
import { LoginComponent } from './usuario/login/login.component';

const routes: Routes = [
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
    component: MenuVerticalMembroComponent
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
