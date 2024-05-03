import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Vale } from '../models/vale';

const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root'
})
export class ValeService {
  
  constructor(private http: HttpClient) {}
  
  public getVales(): Observable<any> {
    return this.http.get<any>(BACK_URL + 'api/vales');
  }

  public getValeUsuario(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.get<any>(BACK_URL + 'api/vales/usuario', { params: param });
  }

  public nuevoVale(email: string): Observable<any> {
    const param = new HttpParams().append('email', email);
    return this.http.post<any>(BACK_URL + 'api/vales/nuevo', { params: param });
  }

}
