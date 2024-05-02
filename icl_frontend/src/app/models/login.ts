export class Login {
  email: string;
  contrasena: string;

  constructor(
    email: string = '',
    contrasena: string = ''
  ) {
    this.email = email;
    this.contrasena = contrasena;
  }
}
