import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/usuarios/login/login.component';
import { IndexComponent } from './components/general/index/index.component';
import { AdminGuard } from './guards/admin.guard';
import { AuthGuard } from './guards/auth.guard';
import { LoginGuard } from './guards/login.guard';
import { RegistroComponent } from './components/usuarios/registro/registro.component';
import { PerfilComponent } from './components/usuarios/perfil/perfil.component';
import { SolicitudValeComponent } from './components/vales/solicitud-vale/solicitud-vale.component';
import { ValesUsuarioComponent } from './components/vales/vales-usuario/vales-usuario.component';
import { PerfilNegocioComponent } from './components/negocios/perfil-negocio/perfil-negocio.component';
import { ValesNegocioComponent } from './components/vales/vales-negocio/vales-negocio.component';
import { ListaNegociosComponent } from './components/negocios/lista-negocios/lista-negocios.component';

const routes: Routes = [{ path: '', component: IndexComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'registro', component: RegistroComponent, canActivate: [LoginGuard] },
  // { path: 'email-password', component: EmailPasswordComponent, canActivate: [LoginGuard] },
  // { path: 'cambiar-password/:tokenPassword', component: CambiarPasswordComponent, canActivate: [LoginGuard] },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },
  { path: 'perfil-negocio', component: PerfilNegocioComponent, canActivate: [AuthGuard] },
   { path: 'lista-negocios', component: ListaNegociosComponent, canActivate: [AuthGuard] },
   { path: 'solicitud-vale', component: SolicitudValeComponent, canActivate: [AuthGuard] },
   { path: 'vales-usuario', component: ValesUsuarioComponent, canActivate: [AuthGuard] },
   { path: 'vales-negocio', component: ValesNegocioComponent, canActivate: [AuthGuard] },
  // { path: 'admin/usuarios', component: ListaUsuariosComponent, canActivate: [AdminGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
