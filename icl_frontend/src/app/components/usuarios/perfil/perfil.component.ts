import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CambiarContrasena } from 'src/app/models/cambiar-contrasena';
import { Jwt } from 'src/app/models/jwt';
import { PerfilUsuario } from 'src/app/models/perfil-usuario';
import { AuthService } from 'src/app/services/auth.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {
  perfilUsuario = new PerfilUsuario();
  error = false;
  mensaje = '';
  cambiarContrasena = new CambiarContrasena();
  errorPass = false;
  mensajePass = '';

  id: FormControl;
  nombre: FormControl;
  apellidos: FormControl;
  documento: FormControl;
  email: FormControl;
  perfilUsuarioForm: FormGroup;

  contrasena: FormControl;
  confirmaContrasena: FormControl;
  cambiarContrasenaForm: FormGroup;

  constructor(
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    const emailToken = this.authService.getEmail();
    this.usuarioService.getPerfilUsuario(emailToken).subscribe(
      (data) => {
        this.id = new FormControl(data.id);
        this.nombre = new FormControl(data.nombre, Validators.required);
        this.apellidos = new FormControl(data.apellidos, Validators.required);
        this.documento = new FormControl(data.documento, Validators.required);
        this.email = new FormControl(data.email, Validators.required);

        this.perfilUsuarioForm = this.formBuilder.group({
          id: this.id,
          nombre: this.nombre,
          apellidos: this.apellidos,
          documento: this.documento,
          email: this.email,
        });
      },
      (err) => {
        this.error = true;
        this.mensaje = err.error;
        console.log(err);
      }
    );

    this.contrasena = new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]);
    this.confirmaContrasena = new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]);

    this.cambiarContrasenaForm = this.formBuilder.group({
      contrasena: this.contrasena,
      confirmaContrasena: this.confirmaContrasena,
    });
  }

  onActualizaDatos(): void {
    this.perfilUsuario.id = this.id.value;
    this.perfilUsuario.nombre = this.nombre.value;
    this.perfilUsuario.apellidos = this.apellidos.value;
    this.perfilUsuario.documento = this.documento.value;
    this.perfilUsuario.email = this.email.value;

    this.usuarioService.modificar(this.perfilUsuario).subscribe(
      (data) => {
        this.mensaje = data.mensaje;
      },
      (err) => {
        this.error = true;
        this.mensaje = err.error;
      }
    );
  }

   onCambiarContrasena(): void {
    this.cambiarContrasena.email = this.authService.getEmail();
    this.cambiarContrasena.contrasena = this.contrasena.value;
    this.cambiarContrasena.confirmaContrasena = this.confirmaContrasena.value;

    this.authService.cambiarContrasena(this.cambiarContrasena)
      .subscribe(
        (data) => {
          this.mensajePass = data.mensaje;
          const token = this.authService.getToken();
          this.authService.refresh(new Jwt(token));
        },
        (err) => {
          this.mensajePass = err.error;
          this.errorPass = true;
          console.log(err);
        }
      );
  }
}
