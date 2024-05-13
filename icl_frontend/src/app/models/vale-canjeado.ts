
export class ValeCanjeado {
  id: string;
  qr: string;
  emailNegocio: String;
  total: number;
  descuento: number;

  constructor(
    id: string = '',
    qr: string = '',
    emailNegocio: String = '',
    total: number = 0,
    descuento: number = 0
  ) {
    this.id = id;
    this.qr = qr;
    this.emailNegocio = emailNegocio;
    this.total = total;
    this.descuento = descuento;
  }
}