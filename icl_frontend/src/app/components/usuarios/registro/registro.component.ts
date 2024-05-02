import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {
  nuevoUsuario = new Usuario();

  nombre: FormControl;
  apellidos: FormControl;
  documento: FormControl;
  email: FormControl;
  contrasena: FormControl;

  registroForm: FormGroup;

  mensaje = '';
  patternNameValidation = '^[a-zA-Z \\-\x27áéíóú]+';
  patternEmailValidation = '[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';
  patternNifValidation = '^[0-9]{8}[A-Za-z]$';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
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
  }

  onRegister(): void {
    this.nuevoUsuario.nombre = this.nombre.value;
    this.nuevoUsuario.apellidos = this.apellidos.value;
    this.nuevoUsuario.documento = this.documento.value;
    this.nuevoUsuario.email = this.email.value;
    this.nuevoUsuario.contrasena = this.contrasena.value;

    this.authService.registro(this.nuevoUsuario).subscribe(
      (data) => {
        this.router.navigate(['/login']);
      },
      (err) => {
        this.mensaje = err.error;
      }
    );
  }
}