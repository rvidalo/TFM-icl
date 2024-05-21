
import { TipoNegocio } from "./tipo-negocio";

export class Negocio {
  id: string;
  nombre: string;
  direccion: string;
  cif: string;
  email: string;
  contrasena: string;
  valorTotal: number;
  estado: number;
  tipo: TipoNegocio;
  totalCanjeado: number;

  constructor(
    id: string = '',
    nombre: string = '',
    direccion: string = '',
    cif: string = '',
    email: string = '',
    contrasena: string = '',
    valorTotal: number = 0,
    estado: number = 1,
    tipo: TipoNegocio = new TipoNegocio,
    totalCanjeado: number = 0
  ) {
    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.cif = cif;
    this.email = email;
    this.contrasena = contrasena;
    this.valorTotal = valorTotal;
    this.estado = estado;
    this.tipo = tipo;
    this.totalCanjeado = totalCanjeado;
  }
}