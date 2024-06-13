import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Negocio } from '../models/negocio';
import { PerfilNegocio } from '../models/perfil-negocio';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class NegocioService {

  constructor(private http: HttpClient) {}
  
  public getNegocios(): Promise<any> {
    return this.http.get<any>(BACK_URL + 'api/negocios').toPromise();
  }

  public getNegociosAceptados(): Promise<any> {
    return this.http.get<any>(BACK_URL + 'api/negocios/aceptados').toPromise();
  }
  
  public aceptar(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/negocios/aceptar', { params: param }).toPromise();
  }

  public rechazar(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/negocios/rechazar', { params: param }).toPromise();
  }

  public getTiposNegocio(): Promise<any> {
    return this.http.get<any>(BACK_URL + 'api/auth/tipos').toPromise();
  }

  public nuevo(negocio: Negocio): Promise<any> {
    return this.http.post<Negocio>(BACK_URL + 'api/negocios/nuevo', Negocio).toPromise();
  }

  public modificar(perfilNegocio: PerfilNegocio): Promise<any> {
    return this.http.post<PerfilNegocio>(BACK_URL + 'api/negocios/modificar', perfilNegocio).toPromise();
  }

  public getPerfilNegocio(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<PerfilNegocio>(BACK_URL + 'api/negocios/perfil', { params: param }).toPromise();
  }

}
