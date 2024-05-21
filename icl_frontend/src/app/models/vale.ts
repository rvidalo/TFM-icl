import { Usuario } from "./usuario";

export class Vale {
  id: string;
  valorTotal: string;
  qr: string;
  fechaLimite: Date;
  fechaRegistro: Date;
  totalCanjeado: number;
  usuario: Usuario;

  constructor(
    id: string = '',
    valorTotal: string = '50€',
    qr: string = '',
    usuario: Usuario = new Usuario,
    fechaLimite: Date = new Date,
    fechaRegistro: Date = new Date,
    totalCanjeado: number = 0
  ) {
    this.id = id;
    this.valorTotal = valorTotal;
    this.qr = qr;
    this.usuario = usuario;
    this.fechaLimite = fechaLimite;
    this.fechaRegistro = fechaRegistro;
    this.totalCanjeado = totalCanjeado;
  }
}