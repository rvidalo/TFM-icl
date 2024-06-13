import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Jwt } from '../models/jwt';
import { Usuario } from '../models/usuario';
import { Login } from '../models/login';
import { Negocio } from '../models/negocio';

const TOKEN_KEY = 'AuthToken';
const EMAIL_DETALLE = 'EmailDetalle';
const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private router: Router, private http: HttpClient) {}

  public setEmailDetalle(email: string): void {
    window.sessionStorage.removeItem(EMAIL_DETALLE);
    window.sessionStorage.setItem(EMAIL_DETALLE, email);
  }

  public getEmailDetalle(): string | null {
    return sessionStorage.getItem(EMAIL_DETALLE);
  }

  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getEmail(): string | null {
    const token = this.getToken();

    if (token == null || !this.isLogged()) {
      return null;
    }

    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    
    return values.sub;
  }

  public isLogged(): boolean {
    if (this.getToken()) {
      return true;
    }
    return false;
  }

  public isAdmin(): boolean {
    const token = this.getToken();

    if (!token || !this.isLogged()) {
      return false;
    }

    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const rol = values.rol[0].authority;
    if (rol == 'ADMIN') {
      return true;
    }
    return false;
  }
  
  public isUsuario(): boolean {
    const token = this.getToken();

    if (!token || !this.isLogged()) {
      return false;
    }

    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const rol = values.rol[0].authority;
    if (rol == 'USUARIO') {
      return true;
    }
    return false;
  }

  public isNegocio(): boolean {
    const token = this.getToken();

    if (!token || !this.isLogged()) {
      return false;
    }

    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const rol = values.rol[0].authority;
    if (rol == 'NEGOCIO') {
      return true;
    }
    return false;
  }

  public logOut(): void {
    window.sessionStorage.clear();
    this.router.navigateByUrl('/');
  }

   public registro(nuevoUsuario: Usuario): Promise<any> {
     return this.http.post<Jwt>(BACK_URL + 'api/auth/registroUsuario', nuevoUsuario).toPromise();
   }

   public registroNegocio(nuevoNegocio: Negocio): Promise<any> {
     return this.http.post<Jwt>(BACK_URL + 'api/auth/registroNegocio', nuevoNegocio).toPromise();
   }

   public login(login: Login): Promise<Jwt> {
    console.log(BACK_URL + 'api/auth/login');
    console.log(login)
    return this.http.post<Jwt>(BACK_URL + 'api/auth/login', login).toPromise();
  }

}
