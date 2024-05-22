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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { QRCodeModule } from 'angularx-qrcode';
import { CanjearValeComponent } from './components/vales/canjear-vale/canjear-vale.component';
import { ListaUsuariosAdminComponent } from './components/usuarios/lista-usuarios-admin/lista-usuarios-admin.component';
import { ListaNegociosAdminComponent } from './components/negocios/lista-negocios-admin/lista-negocios-admin.component';
import { MatDialogModule } from '@angular/material/dialog';

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
    CanjearValeComponent,
    ListaUsuariosAdminComponent,
    ListaNegociosAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule, 
    MatTableModule,
    MatSortModule,
    MatDialogModule,
    QRCodeModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
