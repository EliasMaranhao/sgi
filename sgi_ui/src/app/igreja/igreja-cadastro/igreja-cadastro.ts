import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, ɵInternalFormsSharedModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { IgrejaService } from '../igreja-service';
import { Matriz } from '../../models';
import { MatButtonModule } from '@angular/material/button';
import { NgxMaskDirective } from 'ngx-mask';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { IgrejaBarraNavegacao } from '../igreja-barra-navegacao/igreja-barra-navegacao';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-igreja-cdastro',
  imports: [
    FlexLayoutModule,
    MatCardModule,
    MatFormField,
    MatIconModule,
    MatInputModule,
    MatTabsModule,
    MatRadioModule,
    MatSelectModule,
    ɵInternalFormsSharedModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgxMaskDirective,
    MatSnackBarModule,
    IgrejaBarraNavegacao
],
  templateUrl: './igreja-cadastro.html',
  styleUrl: './igreja-cadastro.scss',
})
export class IgrejaCadastro implements OnInit{

  igrejaForm: FormGroup;
  matrizes: Matriz[] = [];
  idParaEdicao!: number;

  private snackBar = inject(MatSnackBar);
  private route = inject(ActivatedRoute);
  private fb = inject(FormBuilder);
  private igrejaService = inject(IgrejaService);
  private cdr = inject(ChangeDetectorRef);

  constructor(){

    this.igrejaForm = this.fb.group({
      id: [''],
      nome: ['', [Validators.required]],
      matriz: this.fb.group({
        id: ['', [Validators.required]]
      }),
      dataInauguracao: ['', [Validators.required]],
      pastorDirigente: ['', [Validators.required]],
      endereco: this.fb.group({
        rua: ['', [Validators.required]],
        numero: ['', [Validators.required]],
        complemento: [''],
        bairro: ['', [Validators.required]],
        cep: ['', [Validators.required]],
        cidade: ['', [Validators.required]],
        uf: ['', [Validators.required]],
        pais: ['', [Validators.required]]
      }),
      contato: this.fb.group({
        telefone: ['', [Validators.required]],
        email: ['', [Validators.required]],
        whatsapp: ['', [Validators.required]],
        instagram: ['']
      }),
      cnpj: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.carregarOpcoesSelect(); // Nova função para o Select

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.idParaEdicao = +id;
      this.carregarDados(this.idParaEdicao);
    }
  }

  carregarDados(id: number){
    this.igrejaService.buscarFilialPeloId(id).subscribe({
      next: response => {
        this.igrejaForm.get('id')?.setValue(response.id);
        this.igrejaForm.get('nome')?.setValue(response.nome);
        this.igrejaForm.get('pastorDirigente')?.setValue(response.pastorDirigente);
        this.igrejaForm.get('dataInauguracao')?.setValue(response.dataInauguracao);
        this.igrejaForm.get('endereco')?.setValue(response.endereco);
        this.igrejaForm.get('contato')?.setValue(response.contato);
        this.igrejaForm.get('cnpj')?.setValue(response.cnpj);
        this.igrejaForm.get('matriz.id')?.setValue(response.matriz.id);
      },
      error: erro => {
        console.log('Erro: ', erro);
      }
    });
  }

  carregarOpcoesSelect(){
    this.igrejaService.buscarMatrizTodas().subscribe({
      next: response => {
        this.matrizes = response;
        this.cdr.detectChanges()
      },
      error: erro => {
        console.log(erro);
      }
    })
  }

  compararObjetos(o1: any, o2: any): boolean {
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }

  salvar(){
    this.igrejaService.cadastrarFilial(this.igrejaForm.value).subscribe({
      next: (resp) => {
        this.snackBar.open('Filial cadastrada com sucesso!', 'Fechar', {
          duration: 3000,           // 3 segundos
          horizontalPosition: 'end', // Direita
          verticalPosition: 'top',   // Cima
          panelClass: ['success-snackbar'] // Classe CSS opcional
        });
        this.igrejaForm.reset;
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
    this.igrejaService.atualizarFilial(this.idParaEdicao, this.igrejaForm.value).subscribe({
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
