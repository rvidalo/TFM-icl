import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { IndexComponent } from './components/index/index.component';
import { AdminGuard } from './guards/admin.guard';
import { AuthGuard } from './guards/auth.guard';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [{ path: '', component: IndexComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  // { path: 'registro', component: RegistroComponent, canActivate: [LoginGuard] },
  // { path: 'email-password', component: EmailPasswordComponent, canActivate: [LoginGuard] },
  // { path: 'cambiar-password/:tokenPassword', component: CambiarPasswordComponent, canActivate: [LoginGuard] },
  // { path: 'perfil-usuario', component: PerfilUsuarioComponent, canActivate: [AuthGuard] },
  // { path: 'negocios', component: NegociosComponent, canActivate: [AuthGuard] },
  // { path: 'mis-vales', component: MisValesComponent, canActivate: [AuthGuard] },
  // { path: 'admin/usuarios', component: ListaUsuariosComponent, canActivate: [AdminGuard] }
,];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
