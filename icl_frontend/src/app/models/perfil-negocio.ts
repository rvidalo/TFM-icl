
import { TipoNegocio } from "./tipo-negocio";

export class PerfilNegocio {
  id: string;
  nombre: string;
  direccion: string;
  cif: string;
  email: string;
  valorTotal: number;
  tipo: TipoNegocio;
  totalCanjeado: number;

  constructor(
    id: string = '',
    nombre: string = '',
    direccion: string = '',
    cif: string = '',
    email: string = '',
    valorTotal: number = 0,
    tipo: TipoNegocio = new TipoNegocio,
    totalCanjeado: number = 0
  ) {
    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.cif = cif;
    this.email = email;
    this.valorTotal = valorTotal;
    this.tipo = tipo;
    this.totalCanjeado = totalCanjeado;
  }
}