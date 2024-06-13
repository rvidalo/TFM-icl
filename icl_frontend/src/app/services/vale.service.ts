import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Vale } from '../models/vale';
import { ValeCanjeado } from '../models/vale-canjeado';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class ValeService {
  
  constructor(private http: HttpClient) {}
  
  public getVales(): Promise<any> {
    return this.http.get<any>(BACK_URL + 'api/vales').toPromise();
  }

  public getCanjeadosUsuario(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/canjeados/usuario', { params: param }).toPromise();
  }
  
  public getCanjeadosNegocio(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/canjeados/negocio', { params: param }).toPromise();
  }

  public getValeUsuario(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/vales/usuario', { params: param }).toPromise();
  }
  
  public nuevoVale(email: string): Promise<any> {
    const param = new HttpParams().append('email', email);
    return this.http.post<any>(BACK_URL + 'api/vales/nuevo?email='+email, null).toPromise();
  }
  
  public canjearVale(valeCanjeado: ValeCanjeado) : Promise<any> {
    return this.http.post<number>(BACK_URL + 'api/canjeados/nuevo', valeCanjeado).toPromise();
  }

}

