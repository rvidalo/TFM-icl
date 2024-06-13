import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { Negocio } from 'src/app/models/negocio';
import { TipoNegocio } from 'src/app/models/tipo-negocio';
import { AuthService } from 'src/app/services/auth.service';
import { NegocioService } from 'src/app/services/negocio.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {
  formularioActivo = 'usuario'; // Formulario de usuario activo por defecto
  nuevoUsuario = new Usuario();
  nuevoNegocio = new Negocio();
  tiposNegocio: TipoNegocio[] = [];

  nombre: FormControl;
  apellidos: FormControl;
  documento: FormControl;
  email: FormControl;
  contrasena: FormControl;

  registroForm: FormGroup;
  registroNegocioForm: FormGroup;

  nombreNegocio: FormControl;
  direccionNegocio: FormControl;
  cifNegocio: FormControl;
  emailNegocio: FormControl;
  contrasenaNegocio: FormControl;
  tipoNegocio: FormControl;

  mensaje = '';
  patternNameValidation = '^[a-zA-Z \\-\x27áéíóú]+';
  patternEmailValidation = '[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';
  patternNifValidation = '^[0-9]{8}[A-Za-z]$';
  patternCifValidation = '^[A-Za-z][0-9]{7}[A-Za-z]$';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private negocioService: NegocioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.nombre = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternNameValidation),
    ]);
    this.apellidos = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternNameValidation),
    ]);
    this.documento = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternNifValidation),
    ]);
    this.email = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternEmailValidation),
    ]);
    this.contrasena = new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]);

    this.registroForm = this.formBuilder.group({
      nombre: this.nombre,
      apellidos: this.apellidos,
      documento: this.documento,
      email: this.email,
      contrasena: this.contrasena,
    });

    this.nombreNegocio = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternNameValidation),
    ]);
    this.direccionNegocio = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternNameValidation),
    ]);
    this.cifNegocio = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternCifValidation),
    ]);
    this.emailNegocio = new FormControl('', [
      Validators.required,
      Validators.pattern(this.patternEmailValidation),
    ]);
    this.tipoNegocio = new FormControl('', [
      Validators.required
    ]);
    this.contrasenaNegocio = new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]);
    this.registroNegocioForm = this.formBuilder.group({
      nombreNegocio: this.nombreNegocio,
      direccionNegocio: this.direccionNegocio,
      cifNegocio: this.cifNegocio,
      emailNegocio: this.emailNegocio,
      contrasenaNegocio: this.contrasenaNegocio,
      tipoNegocio: this.tipoNegocio
    });

    this.cargarTiposNegocio();
  }

  mostrarFormulario(tipo: string): void {
    this.formularioActivo = tipo;
  }

  async onRegisterUsuario(): Promise<void> {
    this.nuevoUsuario.nombre = this.nombre.value;
    this.nuevoUsuario.apellidos = this.apellidos.value;
    this.nuevoUsuario.documento = this.documento.value;
    this.nuevoUsuario.email = this.email.value;
    this.nuevoUsuario.contrasena = this.contrasena.value;

    try {
      const data = await this.authService.registro(this.nuevoUsuario);
      this.mensaje = 'Registro finalizado correctamente. ¡Revisa tu correo!';
    } catch (err) {
      this.mensaje = err.error;
    }
  }

  async onRegisterNegocio(): Promise<void> {
    this.nuevoNegocio.nombre = this.nombreNegocio.value;
    this.nuevoNegocio.direccion = this.direccionNegocio.value;
    this.nuevoNegocio.cif = this.cifNegocio.value;
    this.nuevoNegocio.email = this.emailNegocio.value;
    this.nuevoNegocio.contrasena = this.contrasenaNegocio.value;
    this.nuevoNegocio.tipo = this.tipoNegocio.value;

    try {
      const data = await this.authService.registroNegocio(this.nuevoNegocio);
      this.mensaje = 'Registro finalizado correctamente. ¡Revisa tu correo!';
    } catch (err) {
      this.mensaje = err.error;
    }
  }

  async cargarTiposNegocio(): Promise<void> {
    try {
      this.tiposNegocio = await this.negocioService.getTiposNegocio();
    } catch (error) {
      console.error('Error al cargar tipos de negocio:', error);
    }
  }
}