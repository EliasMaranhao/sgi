import { Component, inject  } from '@angular/core';
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
    MatButtonModule
  ],
  templateUrl: './membro-cadastro.html',
  styleUrl: './membro-cadastro.scss',
})
export class MembroCadastro {
  membroForm: FormGroup;
  private dialog = inject(MatDialog); // Injeção moderna (inject)

  constructor(private fb: FormBuilder){
    this.membroForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(10)]],
      dataNascimento: ['', [Validators.required, Validators.minLength(8)]],
      documento: ['', [Validators.required]],
      tipo: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      celular: [''],
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
}
