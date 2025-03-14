import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuVerticalMembroComponent } from './menu-vertical-membro/menu-vertical-membro.component';
import { RouterModule } from '@angular/router';
import { CadastrarMembroComponent } from './cadastrar-membro/cadastrar-membro.component';
import { PesquisarMembroComponent } from './pesquisar-membro/pesquisar-membro.component';



@NgModule({
  declarations: [
    MenuVerticalMembroComponent,
    CadastrarMembroComponent,
    PesquisarMembroComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    MenuVerticalMembroComponent,
    CadastrarMembroComponent
  ]
})
export class MembroModule { }
