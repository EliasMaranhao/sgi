import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastrarMinisterioComponent } from './cadastrar-ministerio/cadastrar-ministerio.component';
import { MenuVerticalMinisterioComponent } from './menu-vertical-ministerio/menu-vertical-ministerio.component';
import { RouterModule } from '@angular/router';
import { PesquisarMinisterioComponent } from './pesquisar-ministerio/pesquisar-ministerio.component';

@NgModule({
  declarations: [
    CadastrarMinisterioComponent,
    MenuVerticalMinisterioComponent,
    PesquisarMinisterioComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    CadastrarMinisterioComponent,
    MenuVerticalMinisterioComponent,
    RouterModule
  ]
})
export class MinisterioModule { }
