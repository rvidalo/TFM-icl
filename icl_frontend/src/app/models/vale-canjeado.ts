
export class ValeCanjeado {
  id: string;
  qr: string;
  emailNegocio: String;
  total: number;
  descuento: number;
  fechaRegistro: Date;

  constructor(
    id: string = '',
    qr: string = '',
    emailNegocio: String = '',
    total: number = 0,
    descuento: number = 0,
    fechaRegistro: Date = new Date()
  ) {
    this.id = id;
    this.qr = qr;
    this.emailNegocio = emailNegocio;
    this.total = total;
    this.descuento = descuento;
    this.fechaRegistro = fechaRegistro;
  }
}