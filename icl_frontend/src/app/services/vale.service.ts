import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Vale } from '../models/vale';
import { ValeCanjeado } from '../models/vale-canjeado';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class ValeService {
  
  constructor(private http: HttpClient) {}
  
  public getVales(): Observable<any> {
    return this.http.get<any>(BACK_URL + 'api/vales');
  }

  public getCanjeadosUsuario(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/canjeados/usuario', { params: param });
  }
  
  public getCanjeadosNegocio(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/canjeados/negocio', { params: param });
  }

  public getValeUsuario(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/vales/usuario', { params: param });
  }
  
  public nuevoVale(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    console.log('Nuevo vale: ' + BACK_URL + 'api/vales/nuevo?email=' + email);
    return this.http.post<any>(BACK_URL + 'api/vales/nuevo?email='+email, null);
  }
  
  public canjearValue(valeCanjeado: ValeCanjeado) : Observable<any> {
    return this.http.post<number>(BACK_URL + 'api/canjeados/nuevo', valeCanjeado);
  }

  calcularTotalDescuentos(valesCanjeados: ValeCanjeado[]): number {
    let totalDescuentos = 0;
    if(valesCanjeados != null){
      valesCanjeados.forEach(item => {
        totalDescuentos += item.descuento;
      });
    }
    return totalDescuentos;
  }
}

