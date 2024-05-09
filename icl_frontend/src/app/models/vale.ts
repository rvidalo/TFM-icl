import { Usuario } from "./usuario";

export class Vale {
  id: string;
  valorTotal: string;
  qr: string;
  fechaLimite: Date;
  usuario: Usuario;

  constructor(
    id: string = '',
    valorTotal: string = '50€',
    qr: string = '',
    usuario: Usuario = new Usuario,
    fechaLimite: Date = new Date,
  ) {
    this.id = id;
    this.valorTotal = valorTotal;
    this.qr = qr;
    this.usuario = usuario;
    this.fechaLimite = fechaLimite;
  }
}