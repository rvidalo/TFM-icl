import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CambiarContrasena } from 'src/app/models/cambiar-contrasena';
import { Jwt } from 'src/app/models/jwt';
import { PerfilNegocio } from 'src/app/models/perfil-negocio';
import { TipoNegocio } from 'src/app/models/tipo-negocio';
import { AuthService } from 'src/app/services/auth.service';
import { NegocioService } from 'src/app/services/negocio.service';

@Component({
  selector: 'app-perfil-negocio',
  templateUrl: './perfil-negocio.component.html',
  styleUrls: ['./perfil-negocio.component.scss']
})
export class PerfilNegocioComponent implements OnInit {
  perfilNegocio = new PerfilNegocio();
  tiposNegocio: TipoNegocio[] = [];
  error = false;
  mensaje = '';
  cambiarContrasena = new CambiarContrasena();
  errorPass = false;
  mensajePass = '';

  id: FormControl;
  nombre: FormControl;
  direccion: FormControl;
  cif: FormControl;
  email: FormControl;
  tipoNegocio: FormControl;
  perfilNegocioForm: FormGroup;

  contrasena: FormControl;
  confirmaContrasena: FormControl;
  cambiarContrasenaForm: FormGroup;

  constructor(
    private authService: AuthService,
    private negocioService: NegocioService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    const emailToken = this.authService.getEmail();
    this.cargarTiposNegocio();
    this.negocioService.getPerfilNegocio(emailToken).subscribe(
      (data) => {
        console.log('Perfil negocio');
        console.log(data);
        this.id = new FormControl(data.id);
        this.nombre = new FormControl(data.nombre, Validators.required);
        this.direccion = new FormControl(data.direccion, Validators.required);
        this.cif = new FormControl(data.cif, Validators.required);
        this.email = new FormControl(data.email, Validators.required);
        this.tipoNegocio = new FormControl(data.tipo.id, Validators.required);

        this.perfilNegocioForm = this.formBuilder.group({
          id: this.id,
          nombre: this.nombre,
          direccion: this.direccion,
          cif: this.cif,
          email: this.email,
          tipoNegocio: this.tipoNegocio
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
    this.perfilNegocio.id = this.id.value;
    this.perfilNegocio.nombre = this.nombre.value;
    this.perfilNegocio.direccion = this.direccion.value;
    this.perfilNegocio.cif = this.cif.value;
    this.perfilNegocio.email = this.email.value;
    this.perfilNegocio.tipo = this.tipoNegocio.value;
    console.log(this.perfilNegocio);
    console.log('Tipo: ' + this.tipoNegocio.value);
    this.negocioService.modificar(this.perfilNegocio).subscribe(
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

  cargarTiposNegocio(): void {
    this.negocioService.getTiposNegocio().subscribe(
      (data: TipoNegocio[]) => {
        /* console.log('Tipos Negocio');
        console.log(data); */
        this.tiposNegocio = data;
      },
      (error) => {
        console.error('Error al cargar tipos de negocio:', error);
      }
    );
  }
}