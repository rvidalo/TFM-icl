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
  //porcentajeSolicitados:number
 
  constructor(
    //private formBuilder: FormBuilder,
    private valeService: ValeService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {

    const emailToken = this.authService.getEmail();
    console.log('Solicitud vale');
    this.valeService.getVales().subscribe(
      (data) => {
        console.log('Solicitud vale');
        console.log(data);
        this.valesSolicitados = data;
        this.numVales = this.valesSolicitados.length;
      },
      (err) => {
        console.log(err);
      }
    );

    this.valeService.getValeUsuario(emailToken).subscribe(
      (data) => {
        console.log('Solicitud vale empleado');
        console.log(data);
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
    throw new Error('Method not implemented.');
  }
}
