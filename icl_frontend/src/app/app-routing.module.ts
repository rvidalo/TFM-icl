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

const routes: Routes = [{ path: '', component: IndexComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'registro', component: RegistroComponent, canActivate: [LoginGuard] },
  // { path: 'email-password', component: EmailPasswordComponent, canActivate: [LoginGuard] },
  // { path: 'cambiar-password/:tokenPassword', component: CambiarPasswordComponent, canActivate: [LoginGuard] },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },
  // { path: 'negocios', component: NegociosComponent, canActivate: [AuthGuard] },
   { path: 'solicitud-vale', component: SolicitudValeComponent, canActivate: [AuthGuard] },
   { path: 'vales-usuario', component: ValesUsuarioComponent, canActivate: [AuthGuard] },
  // { path: 'admin/usuarios', component: ListaUsuariosComponent, canActivate: [AdminGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
