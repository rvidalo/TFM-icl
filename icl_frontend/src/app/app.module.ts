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
import { PerfilNegocioComponent } from './components/negocios/perfil-negocio/perfil-negocio.component';
import { ValesNegocioComponent } from './components/vales/vales-negocio/vales-negocio.component';
import { ListaNegociosComponent } from './components/negocios/lista-negocios/lista-negocios.component';
import { FiltroNegociosPipe } from './pipes/filtro-negocios.pipe';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';

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
    ValesUsuarioComponent,
    PerfilNegocioComponent,
    ValesNegocioComponent,
    ListaNegociosComponent,
    FiltroNegociosPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule, 
    MatTableModule,
    MatSortModule 
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
