import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';

@Component({
  selector: 'app-igreja-barra-navegacao',
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    RouterLink,
    FlexLayoutModule,
    MatButtonModule
  ],
  templateUrl: './igreja-barra-navegacao.html',
  styleUrl: './igreja-barra-navegacao.scss',
})
export class IgrejaBarraNavegacao {}
