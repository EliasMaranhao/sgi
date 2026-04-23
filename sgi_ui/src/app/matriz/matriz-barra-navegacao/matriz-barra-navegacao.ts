import { Component } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-matriz-barra-navegacao',
  imports: [
    MatIconModule,
    MatToolbarModule,
    RouterLink,
    RouterLinkActive,
    FlexLayoutModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatTooltipModule
  ],
  templateUrl: './matriz-barra-navegacao.html',
  styleUrl: './matriz-barra-navegacao.scss',
})
export class MatrizBarraNavegacao {}
