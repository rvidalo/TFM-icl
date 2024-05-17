import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { ValeService } from 'src/app/services/vale.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  isLogged = false;
  email = '';
  isAdmin = false;
  isUsuario = false;
  isNegocio = false;
  solicitudRealizada = false;

  constructor(
    private authService: AuthService,
    private valeService: ValeService,
  ) {}

  ngOnInit(): void {
    this.isLogged = this.authService.isLogged();
    this.isAdmin = this.authService.isAdmin();
    this.isUsuario = this.authService.isUsuario();
    this.isNegocio = this.authService.isNegocio();
    if (this.isLogged) {
      this.email = this.authService.getEmail();
    }
    this.closeOnClick();

    if(this.isUsuario){
      this.valeService.getValeUsuario(this.email).subscribe(
        (data) => {
          console.log("menu usuario con vale?")
          console.log(data);
          if (data != null){
            this.solicitudRealizada = true;
          }
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  onLogOut(): void {
    this.email = '';
    this.isLogged = false;
    this.authService.logOut();
    window.location.reload();
  }

  closeOnClick(): void {
    document.addEventListener('DOMContentLoaded', (event) => {
      const links = document.querySelectorAll('nav a');
      const buttonClose = document.querySelector(
        '.navbar-toggler'
      ) as HTMLElement;
      links.forEach((a) => {
        a.addEventListener('click', () => {
          if (buttonClose.getAttribute('aria-expanded') === 'true') {
            buttonClose.click();
          }
        });
      });
    });
  }
}
