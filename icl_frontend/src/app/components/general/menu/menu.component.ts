import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

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

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.isLogged = this.authService.isLogged();
    this.isAdmin = this.authService.isAdmin();
    this.isUsuario = this.authService.isUsuario();
    this.isNegocio = this.authService.isNegocio();
    if (this.isLogged) {
      this.email = this.authService.getEmail();
    }
    this.closeOnClick();
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
