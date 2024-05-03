export class Usuario {
  id: string;
  nombre: string;
  apellidos: string;
  documento: string;
  email: string;
  contrasena: string;

  constructor(
    id: string = '',
    nombre: string = '',
    apellidos: string = '',
    documento: string = '',
    email: string = '',
    contrasena: string = ''
  ) {
    this.id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.documento = documento;
    this.email = email;
    this.contrasena = contrasena;
  }
}