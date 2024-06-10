import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Login } from 'src/app/models/login';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { ReactiveFormsModule } from '@angular/forms'; // Importa ReactiveFormsModule

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginUsuario = new Login();

  email: FormControl;
  contrasena: FormControl;
  loginForm: FormGroup;

  mensaje: '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.email = new FormControl('', Validators.required);
    this.contrasena = new FormControl('', Validators.required);

    this.loginForm = this.formBuilder.group({
      email: this.email,
      contrasena: this.contrasena,
    });
  }

  public onLogin(): void {
    this.loginUsuario.email = this.email.value;
    this.loginUsuario.contrasena = this.contrasena.value;

    this.authService.login(this.loginUsuario).subscribe(
      (data) => {
        if (data) {
          this.authService.setToken(data.token);
          window.location.href = '/';
        } else {
          console.error('Error: datos nulos recibidos');
        }
      },
      (err) => {
        let msg = err.error && err.error.mensaje ? err.error.mensaje : 'El email o contraseña no son correctos';
        this.mensaje = msg;
        console.error('Error en la solicitud:', err);
      } 
    );
  }
}