import { Component, inject, OnInit } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { IgrejaService } from '../../igreja/igreja-service';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatrizBarraNavegacao } from '../matriz-barra-navegacao/matriz-barra-navegacao';
import { MatTabsModule } from '@angular/material/tabs';
import { IgrejaConsulta } from '../../igreja/igreja-consulta/igreja-consulta';
import { ActivatedRoute } from '@angular/router';
import { Filial, Matriz } from '../../models';

@Component({
  selector: 'app-matriz-cadastro',
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    FlexLayoutModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatInputModule,
    MatSelectModule,
    MatrizBarraNavegacao,
    MatTabsModule,
    IgrejaConsulta
  ],
  templateUrl: './matriz-cadastro.html',
  styleUrl: './matriz-cadastro.scss',
})
export class MatrizCadastro implements OnInit{
  matrizForm: FormGroup;
  selecionado?: string;
  idParaEdicao!: number;
  filiais: Filial[] = [];

  private snackBar = inject(MatSnackBar);
  private fb = inject(FormBuilder);
  private igrejaService = inject(IgrejaService);
  private route = inject(ActivatedRoute);

  constructor(){
    this.matrizForm = this.fb.group({
      id: [''],
      nome: ['', [Validators.required]],
      pastorPresidente: ['', [Validators.required]],
      vicePresidente: ['', [Validators.required]],
      denominacao: ['', [Validators.required]]
    });
  }
  ngOnInit(): void {
    this.idParaEdicao = +(this.route.snapshot.paramMap.get('id') || 0);

    if (this.idParaEdicao) {
      this.carregarDados(this.idParaEdicao);
    }
  }

  carregarDados(id: number){
    this.igrejaService.buscarMatrizPeloId(id).subscribe({
      next: response => {
        this.matrizForm.get('id')?.setValue(response.id);
        this.matrizForm.get('nome')?.setValue(response.nome);
        this.matrizForm.get('pastorPresidente')?.setValue(response.pastorPresidente)
        this.matrizForm.get('vicePresidente')?.setValue(response.vicePresidente)
        this.matrizForm.get('denominacao')?.setValue(response.denominacao)
        this.buscarFiliaisPeloMatrizId(response.id)
      },
      error: erro => {
        console.log('Erro: ', erro);
      }
    });
  }

  buscarFiliaisPeloMatrizId(id: number){
    this.igrejaService.buscarFiliaisPeloMatrizId(id).subscribe({
      next: response => {
        this.filiais = response;
      },
      error: erro => {
        console.log(erro);
      }
    })
  }

  removerDaLista(filial: Filial) {
    // 1. Filtramos a lista (isso cria uma NOVA referência de array)
    this.filiais = this.filiais.filter(f => f.id !== filial.id);
    
    // 2. Como a referência de 'this.minhasFiliais' mudou, 
    // o Angular dispara automaticamente o Setter @Input() do filho,
    // e sua tabela atualiza sozinha!
  }

  salvar(){
    this.igrejaService.cadastrarMatriz(this.matrizForm.value).subscribe({
      next: (resp) => {
        this.snackBar.open('Matriz criada com sucesso!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['success-snackbar'] // Classe CSS opcional
        });
      },

      error: erro => {
        this.snackBar.open('Não foi possivel registrar neste momento, por favor informe ao administrador!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['error-snackbar'] // Classe CSS opcional
        });
      }
    })
  }

  atualizar(){
    this.igrejaService.atualizarMatriz(this.idParaEdicao, this.matrizForm.value).subscribe({
      next: response => {
          this.snackBar.open('Atualização realizada com sucesso!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['success-snackbar'] // Classe CSS opcional
        });
      },
      error: erro => {
        console.log(erro)
      }
    })
  }
}
