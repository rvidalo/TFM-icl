import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Negocio } from '../models/negocio';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class NegocioService {

  constructor(private http: HttpClient) {}
  
  public getNegocio(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/negocios/email', { params: param });
  }

  public getNegocios(): Observable<any> {
    return this.http.get<any>(BACK_URL + 'api/negocios');
  }

  public getNegociosAceptados(): Observable<any> {
    return this.http.get<any>(BACK_URL + 'api/negocios/aceptados');
  }
  
  public aceptar(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/negocios/aceptar', { params: param });
  }

  public rechazar(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/negocios/rechazar', { params: param });
  }

  public getTiposNegocio(): Observable<any> {
    return this.http.get<any>(BACK_URL + 'api/negocios/tipo');
  }

  public nuevo(negocio: Negocio): Observable<any> {
    return this.http.post<Negocio>(BACK_URL + 'api/negocios/nuevo', Negocio);
  }

  public modificar(negocio: Negocio): Observable<any> {
    return this.http.post<Negocio>(BACK_URL + 'api/negocios/modificar', negocio);
  }
}
