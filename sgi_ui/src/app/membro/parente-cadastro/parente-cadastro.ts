import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';


export interface PeriodicElement {
  nome: string;
  parentesco: string;
}

// 2. Dados de exemplo
const ELEMENT_DATA: PeriodicElement[] = [
  {nome: 'Jose da Silva', parentesco: 'PAI'},
  {nome: 'Maria Antonia Silva', parentesco: 'IRMÃ'},
  {nome: 'Marcos Pontes Mendes', parentesco: 'IRMÃO'},
  {nome: 'Janice Antunes', parentesco: 'MÃE'},
];

@Component({
  selector: 'app-parente-cadastro',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    FlexLayoutModule,
    MatIconModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule
  ],
  templateUrl: './parente-cadastro.html',
  styleUrl: './parente-cadastro.scss',
})
export class ParenteCadastro {

  displayedColumns: string[] = ['nome', 'parentesco'];
  dataSource = ELEMENT_DATA;

  constructor(private dialogRef: MatDialogRef<ParenteCadastro>) {}

  close() {
    this.dialogRef.close();
  }
}
