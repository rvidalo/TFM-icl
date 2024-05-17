
import { TipoNegocio } from "./tipo-negocio";

export class PerfilNegocio {
  id: string;
  nombre: string;
  direccion: string;
  cif: string;
  email: string;
  valorTotal: number;
  tipo: TipoNegocio;

  constructor(
    id: string = '',
    nombre: string = '',
    direccion: string = '',
    cif: string = '',
    email: string = '',
    valorTotal: number = 0,
    tipo: TipoNegocio = new TipoNegocio,
  ) {
    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.cif = cif;
    this.email = email;
    this.valorTotal = valorTotal;
    this.tipo = tipo;
  }
}