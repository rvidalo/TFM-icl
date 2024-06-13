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

  async ngOnInit(): Promise<void> {
    const emailToken = this.authService.getEmail();

    try {
      // Obtener vales solicitados
      this.valesSolicitados = await this.valeService.getVales();
      this.numVales = this.valesSolicitados.length;

      // Verificar si el usuario tiene un vale
      this.valeUsuario = await this.valeService.getValeUsuario(emailToken);
      if (this.valeUsuario != null) {
        this.solicitudRealizada = true;
      }
    } catch (error) {
      console.log(error);
    }
  }

  // Método para calcular el porcentaje de vales entregados
  porcentajeValesEntregados(): number {
    return (this.numVales / 1000) * 100;
  }

  async onSolicitudVale(): Promise<void> {
    try {
      // Hacer solicitud de un nuevo vale
      const nuevoValeData = await this.valeService.nuevoVale(this.authService.getEmail());
      this.mensaje = nuevoValeData.mensaje;

      // Actualizar la lista de vales solicitados después de la solicitud
      const valesData = await this.valeService.getVales();
      this.valesSolicitados = valesData;
      this.numVales = this.valesSolicitados.length;
      this.solicitudRealizada = true;

      // Recargar la página para mostrar el nuevo menú de vales
      window.location.reload();
    } catch (error) {
      this.mensaje = error.error;
      console.log(error);
    }
  }
}
