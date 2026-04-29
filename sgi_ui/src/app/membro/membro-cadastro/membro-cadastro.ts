import { Component, inject, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIconModule } from '@angular/material/icon';
import { NgxMaskDirective } from 'ngx-mask';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { ParenteCadastro } from '../parente-cadastro/parente-cadastro';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-membro-cadastro',
  imports: [
    ReactiveFormsModule,
    MatTabsModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    FlexLayoutModule,
    MatIconModule,
    NgxMaskDirective,
    MatSelectModule,
    MatButtonModule,
    MatRadioModule
  ],
  templateUrl: './membro-cadastro.html',
  styleUrl: './membro-cadastro.scss',
})
export class MembroCadastro implements OnInit{
  membroForm: FormGroup;
  imagePreview: string | null = null;
  idParaEdicao!: number;

  private dialog = inject(MatDialog); // Injeção moderna (inject)
  private fb = inject(FormBuilder);

  constructor(){
    this.membroForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(10)]],
      dataNascimento: ['', [Validators.required, Validators.minLength(8)]],
      documento: ['', [Validators.required]],
      tipo: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      celular: [''],
      foto: [null],

      igreja: ['', [Validators.required]],
      dataBatismo: [[Validators.required]],
      veioOutraIgreja: [false, [Validators.required]],
      recebidoEm: [{value: '', disabled: true}, [Validators.required]],

      endereco: this.fb.group({
        rua: ['', [Validators.required]],
        numero: ['', [Validators.required]],
        complemento: [''],
        cep: ['', [Validators.required]],
        bairro: ['', [Validators.required]],
        cidade: ['', [Validators.required]],
        uf: ['', [Validators.required]],
        pais: ['', [Validators.required]]
      })
    });
  }

  ngOnInit(): void {
    this.membroForm.get('veioOutraIgreja')?.valueChanges.subscribe(veioOutraIgreja => {
      const dataControl = this.membroForm.get('recebidoEm');
      if (veioOutraIgreja) {
        dataControl?.enable(); // Habilita o campo de data
      } else {
        dataControl?.disable(); // Desabilita e limpa o campo
        dataControl?.setValue('');
      }
    });
  }

  salvar(){
    
  }

  buscarCep(){
    
  }

  //modal externo para cadastro de parentes
  openDialog() {
    const dialogRef = this.dialog.open(ParenteCadastro, {
      width: '900px',
      height: '500px',
      disableClose: false // Permite fechar clicando fora
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('O modal foi fechado. Resultado:', result);
    });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      // 1. Atualiza o valor no formulário (o arquivo em si para enviar ao backend)
      // Se já existia um preview anterior, limpamos a memória antes de criar o novo
      this.limparPreviewAnterior();
      // Cria a URL de atalho
      this.imagePreview = URL.createObjectURL(file);

      // Atualiza seu formulário reativo com o arquivo original
      this.membroForm.patchValue({ foto: file });
    }
  }

  limparPreviewAnterior() {
    if (this.imagePreview) {
      URL.revokeObjectURL(this.imagePreview);
      this.imagePreview = null;
    }
  }

  // Boa prática: limpar a memória quando o usuário sai da tela
  ngOnDestroy() {
    this.limparPreviewAnterior();
  }

  atualizar(){
    
  }
}
