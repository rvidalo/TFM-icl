import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PerfilUsuario } from '../models/perfil-usuario';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  constructor(private http: HttpClient) {}

  public getPerfilUsuario(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<PerfilUsuario>(BACK_URL + 'api/usuarios/perfil', { params: param }).toPromise();
  }

  public modificar(perfilUsuario: PerfilUsuario): Promise<any> {
    return this.http.post<PerfilUsuario>(BACK_URL + 'api/usuarios/modificar', perfilUsuario).toPromise();
  }

  public getUsuarios(term: string = '', pageNum: number = 1): Promise<any> {
    const param = new HttpParams()
      .append('term', term)
      .append('pageNum', pageNum.toString());
    return this.http.get<any>(BACK_URL + 'api/usuarios/usuarios', { params: param }).toPromise();
  }

}
