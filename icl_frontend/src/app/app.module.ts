import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/usuarios/login/login.component';
import { IndexComponent } from './components/general/index/index.component';
import { FooterComponent } from './components/general/footer/footer.component';
import { MenuComponent } from './components/general/menu/menu.component';
import { interceptorProvider } from './interceptors/auth-interceptor.service';
import { RegistroComponent } from './components/usuarios/registro/registro.component';
import { PerfilComponent } from './components/usuarios/perfil/perfil.component';
import { SolicitudValeComponent } from './components/vales/solicitud-vale/solicitud-vale.component';
import { ValesUsuarioComponent } from './components/vales/vales-usuario/vales-usuario.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent,
    FooterComponent,
    MenuComponent,
    RegistroComponent,
    PerfilComponent,
    SolicitudValeComponent,
    ValesUsuarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
