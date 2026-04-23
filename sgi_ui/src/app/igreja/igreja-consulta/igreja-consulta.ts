import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { Filial } from '../../models';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { Router } from '@angular/router';
import { IgrejaService } from '../igreja-service';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../utils/ConfirmDialogComponent';

@Component({
  selector: 'app-igreja-consulta',
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatButtonModule,
    FlexLayoutModule,
    MatSnackBarModule
  ],
  templateUrl: './igreja-consulta.html',
  styleUrl: './igreja-consulta.scss',
})
export class IgrejaConsulta{

  dataSource = new MatTableDataSource<Filial>([]);

  private igrejaService = inject(IgrejaService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);

  // Usamos um setter no @Input para interceptar a chegada dos dados
  @Input() set filiais(value: Filial[]) {
    if (value) {
      this.dataSource.data = [...value]; // Aqui a mágica acontece!
    }
  }

  @Output() filialExcluida = new EventEmitter<Filial>();

  displayedColumns: string[] = ['nome', 'pastorDirigente', 'actions'];

  editar(filial: Filial){
    this.router.navigate(['/filial/editar', filial.id]);
  }

  confirmarExclusao(filial: Filial){
    // 1. Abre o Modal
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px'
    });
    // 2. Escuta a resposta (true ou false)
    dialogRef.afterClosed().subscribe(confirmado => {
      if (confirmado) {
        this.excluir(filial);
      }
    });
  }

  buscarTodasasFiliais(){
    this.igrejaService.buscarFilialTodas().subscribe({
      next: response => {
         this.dataSource.data = response;
      },
      error: erro => {
        console.log(erro);
      }
    });
  }

  excluir(filial: Filial){
    this.igrejaService.excluirFilial(filial).subscribe({
      next: response => {
        this.snackBar.open('Registro excluído com sucesso!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['success-snackbar'] // Classe CSS opcional
        });
        this.filialExcluida.emit(filial);
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
}
