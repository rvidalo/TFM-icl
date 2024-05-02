export class CambiarContrasena {
  email: string;
  contrasena: string;
  confirmaContrasena: string;

  constructor(
    email: string = '',
    contrasena: string = '',
    confirmaContrasena: string = ''
  ) {
    this.email = email;
    this.contrasena = contrasena;
    this.confirmaContrasena = confirmaContrasena;
  }
}
