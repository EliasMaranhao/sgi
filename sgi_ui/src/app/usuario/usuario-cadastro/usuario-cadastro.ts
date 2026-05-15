import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormField, MatLabel, MatError } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatSelectModule } from "@angular/material/select";
import { MatProgressSpinner } from "@angular/material/progress-spinner";
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-usuario-cadastro',
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormField,
    MatInputModule,
    MatIconModule,
    MatProgressSpinner,
    MatSelectModule,
    FlexLayoutModule,
    MatButtonModule
],
  templateUrl: './usuario-cadastro.html',
  styleUrl: './usuario-cadastro.scss',
})
export class UsuarioCadastro implements OnInit{

  cadastroForm!: FormGroup;
  hide = true;
  hideConfirm = true;
  isLoading = false;

  private fb = inject(FormBuilder);

  // Listas mockadas (viriam de um Service)
  roles = [
    { value: 'ADMIN', viewValue: 'Administrador' },
    { value: 'GERENTE', viewValue: 'Gerente' },
    { value: 'OPERADOR', viewValue: 'Operador' }
  ];
  filiais = [
    { id: 1, nome: 'Matriz - São Paulo' },
    { id: 2, nome: 'Filial - Rio de Janeiro' },
    { id: 3, nome: 'Filial - Curitiba' }
  ];

  ngOnInit(): void {
    this.cadastroForm = this.fb.group({
      login: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(8)]],
      confirmaSenha: ['', [Validators.required]],
      role: [null, Validators.required],
      filialId: [null, Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  // Validador customizado para comparar senhas
  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const senha = control.get('senha');
    const confirmaSenha = control.get('confirmaSenha');
    return senha && confirmaSenha && senha.value !== confirmaSenha.value 
      ? { passwordMismatch: true } 
      : null;
  }

  onSubmit() {
    if (this.cadastroForm.valid) {
      this.isLoading = true;
      console.log('Dados para cadastro:', this.cadastroForm.value);
      // Simulação de API
      setTimeout(() => this.isLoading = false, 2000);
    }
  }
}
