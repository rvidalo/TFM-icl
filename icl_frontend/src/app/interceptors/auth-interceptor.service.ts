import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, concatMap } from 'rxjs/operators';
import { Jwt } from '../models/jwt';
import { AuthService } from '../services/auth.service';

const AUTHORIZATION = 'Authorization';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!this.authService.isLogged()) {
      return next.handle(req);
    }

    let intReq = req;
    const token = this.authService.getToken();

    if (token) {
      intReq = this.addToken(req, token);
    }
    return next.handle(intReq).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401 && token) {
          const dto: Jwt = new Jwt(token);
          return this.authService.refresh(dto).pipe(
            concatMap((data: any) => {
              this.authService.setToken(data.token);
              intReq = this.addToken(req, data.token);
              return next.handle(intReq);
            })
          );
        }
        if (err.status === 502 && token) {
          this.authService.logOut();
        }
        return throwError(err);
      })
    );
  }

  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      headers: req.headers.set(AUTHORIZATION, 'Bearer' + token),
    });
  }
}

export const interceptorProvider = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true,
  },
];
