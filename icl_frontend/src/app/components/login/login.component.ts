import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginUsuario = new Usuario();

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
        this.authService.setToken(data.token);
        window.location.href = '/';
      },
      (err) => {
        let msg = err.error.mensaje;
        if (!msg) {
          msg = 'Ha ocurrido un error de conexión, vuelva a intentarlo mas tarde';
        }
        this.mensaje = msg;
        console.log(err);
      }
    );
  }
}