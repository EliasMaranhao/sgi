import { AfterViewInit, ChangeDetectorRef, Component, inject, Inject, OnInit } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { Matriz } from '../../models';
import { IgrejaService } from '../../igreja/igreja-service';
import { MatrizBarraNavegacao } from '../matriz-barra-navegacao/matriz-barra-navegacao';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../utils/ConfirmDialogComponent';
import { Router } from '@angular/router';

@Component({
  selector: 'app-matriz-consulta',
  imports: [
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    FlexLayoutModule,
    MatrizBarraNavegacao,
    MatIconModule,
    MatSnackBarModule
  ],
  templateUrl: './matriz-consulta.html',
  styleUrl: './matriz-consulta.scss',
})
export class MatrizConsulta implements OnInit{

  dataSource: Matriz[] = [];
  displayedColumns: string[] = ['pastorPresidente', 'nome', 'actions'];
  
  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);
  private router = inject(Router);
  private igrejaService = inject(IgrejaService);
  private cdr = inject(ChangeDetectorRef);

  //constructor(private igrejaService: IgrejaService, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    this.buscarMatrizTodas();
  }

  buscarMatrizTodas(){
    this.igrejaService.buscarMatrizTodas().subscribe({
      next: response => {
          this.dataSource = response;
          this.cdr.detectChanges()
      },
      error: erro => {
        console.log(erro);
      }
    });
  }

  editar(matriz: Matriz) {
   this.router.navigate(['/matriz/editar', matriz.id]);
  }

  excluir(matriz: Matriz) {
    this.igrejaService.excluirMatriz(matriz).subscribe({
      next: response => {
        this.snackBar.open('Registro excluído com sucesso!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['success-snackbar'] // Classe CSS opcional
        });
      },

      error: erro => {
        this.snackBar.open('Não foi possivel excluir o registro, por favor informe ao administrador!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['error-snackbar'] // Classe CSS opcional
        });
      }
    });
  }

  confirmarExclusao(matriz: Matriz) {
    // 1. Abre o Modal
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px'
    });
    // 2. Escuta a resposta (true ou false)
    dialogRef.afterClosed().subscribe(confirmado => {
      if (confirmado) {
        this.excluir(matriz);
        this.buscarMatrizTodas();
      }
    });
  }
}
