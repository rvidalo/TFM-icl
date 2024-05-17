import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Vale } from 'src/app/models/vale';
import { AuthService } from 'src/app/services/auth.service';
import { ValeService } from 'src/app/services/vale.service';

@Component({
  selector: 'app-solicitud-vale',
  templateUrl: './solicitud-vale.component.html',
  styleUrls: ['./solicitud-vale.component.scss']
})
export class SolicitudValeComponent implements OnInit {
  
  valesSolicitados: Vale[];
  valeUsuario: Vale;
  numVales: number;
  solicitudRealizada: boolean = false;
  mensaje = '';
 
  constructor(
    private valeService: ValeService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {

    const emailToken = this.authService.getEmail();
    this.valeService.getVales().subscribe(
      (data) => {
        this.valesSolicitados = data;
        this.numVales = this.valesSolicitados.length;
      },
      (err) => {
        console.log(err);
      }
    );

    this.valeService.getValeUsuario(emailToken).subscribe(
      (data) => {
        this.valeUsuario = data;
        if (this.valeUsuario != null){
          this.solicitudRealizada = true;
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  // Método para calcular el porcentaje de vales entregados
  porcentajeValesEntregados(): number {
    return (this.numVales / 1000) * 100;
  }

  onSolicitudVale() {
    this.valeService.nuevoVale(this.authService.getEmail()).subscribe(
      (data) => {
        this.mensaje = data.mensaje;
        this.valeService.getVales().subscribe(
          (data) => {
            this.valesSolicitados = data;
            this.numVales = this.valesSolicitados.length;
            this.solicitudRealizada = true;
            //muestra el nuevo menú vales en caso de usuarios
            window.location.reload();
          },
          (err) => {
            this.mensaje = err.error;
            console.log(err);
          }
        );
      },
      (err) => {
        this.mensaje = err.error;
        console.log(err);
      }
    );
  }
}
