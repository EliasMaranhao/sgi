import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { EstruturaComponent } from './estrutura/estrutura.component';



@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent,
    EstruturaComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    EstruturaComponent,
    RouterModule
  ]
})
export class CoreModule { }
