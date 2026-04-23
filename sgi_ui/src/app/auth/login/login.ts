import { Component } from '@angular/core';
import { FormGroup, Validators, ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { LoginService } from '../login-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatProgressSpinnerModule
],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  hide = false;
  isLoading = false;
  errorMessage = '';
  loginForm: FormGroup; 

  constructor(private fb: FormBuilder, 
              private loginService: LoginService, 
              private snackBar: MatSnackBar, 
              private router: Router) 
              {
        this.loginForm = this.fb.group({
        login: ['', [Validators.required, Validators.email]],
        senha: ['', [Validators.required, Validators.minLength(6)]]
      });
    }

  onSubmit() {
    console.log('Chamou onsubmit');
    this.isLoading = true;
    this.errorMessage = '';

    this.loginService.login(this.loginForm.value).subscribe({
      next: () => {
        this.router.navigate(['/filial'])
      },
      error: () => {
        this.errorMessage = 'E-mail ou senha inválidos.';
        this.isLoading = false;
      }
    });
  }
}
