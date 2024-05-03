import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PerfilUsuario } from '../models/perfil-usuario';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  constructor(private http: HttpClient) {}

  public getPerfilUsuario(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<PerfilUsuario>(BACK_URL + 'api/usuarios/perfil', { params: param });
  }

  public modificar(perfilUsuario: PerfilUsuario): Observable<any> {
    return this.http.post<PerfilUsuario>(BACK_URL + 'api/usuarios/modificar', perfilUsuario);
  }

/*   public cambiarPasswordPerfil(
    cambiarPasswordPerfil: CambiarPasswordPerfil
  ): Observable<any> {
    return this.http.post<PerfilUsuario>(
      BACK_URL + 'api/usuario/cambiar-password',
      cambiarPasswordPerfil
    );
  }
 */
  public getUsuarios(term: string = '', pageNum: number = 1): Observable<any> {
    const param = new HttpParams()
      .append('term', term)
      .append('pageNum', pageNum.toString());
    return this.http.get<any>(BACK_URL + 'api/usuarios/usuarios', { params: param });
  }

}
