import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jwt } from '../models/jwt';
// import { CambiarPassword } from '../models/cambiar-password';
// import { EmailPassword } from '../models/email-password';
import { Usuario } from '../models/usuario';
import { Login } from '../models/login';
import { Negocio } from '../models/negocio';
import { CambiarContrasena } from '../models/cambiar-contrasena';

const TOKEN_KEY = 'AuthToken';
const BACK_URL = environment.APIEndpoint;

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private router: Router, private http: HttpClient) {}

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
    const admin = values.roles;
    console.log('Roles: ' + admin)
    if (admin == 0) {
      return false;
    }
    return true;
  }

  public logOut(): void {
    window.sessionStorage.clear();
    window.location.href = '/';
  }

   public registro(nuevoUsuario: Usuario): Observable<any> {
     return this.http.post<Jwt>(BACK_URL + 'api/auth/registroUsuario', nuevoUsuario);
   }

   public registroNegocio(nuevoNegocio: Negocio): Observable<any> {
     return this.http.post<Jwt>(BACK_URL + 'api/auth/registroNegocio', nuevoNegocio);
   }

   public login(login: Login): Observable<Jwt> {
    console.log(BACK_URL + 'api/auth/login');
    console.log(login)
    return this.http.post<Jwt>(BACK_URL + 'api/auth/login', login);
  }

    public refresh(jwt: Jwt): Observable<Jwt> {
      return this.http.post<Jwt>(BACK_URL + 'api/auth/refresh', jwt);
    }
/*   public enviarEmailPassword(emailPassword: EmailPassword): Observable<any> {
    return this.http.post<any>(BACK_URL + 'api/auth/email-password', emailPassword);
  }*/

  public cambiarContrasena(cambiarContrasena: CambiarContrasena): Observable<any> {
    return this.http.post<any>(BACK_URL + 'api/auth/cambiar-contrasena', cambiarContrasena);
  } 
}
