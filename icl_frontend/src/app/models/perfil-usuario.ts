export class PerfilUsuario {
  id: string;
  nombre: string;
  apellidos: string;
  documento: string;
  email: string;
  
  constructor(
    id: string = '',
    nombre: string = '',
    apellidos: string = '',
    documento: string = '',
    email: string = '',
    ) {
    this.id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.documento = documento;
    this.email = email;
  }
}
