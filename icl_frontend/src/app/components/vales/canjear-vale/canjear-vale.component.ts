import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Vale } from 'src/app/models/vale';
import { ValeCanjeado } from 'src/app/models/vale-canjeado';
import { AuthService } from 'src/app/services/auth.service';
import { NegocioService } from 'src/app/services/negocio.service';
import { ValeService } from 'src/app/services/vale.service';

@Component({
  selector: 'app-canjear-vale',
  templateUrl: './canjear-vale.component.html',
  styleUrls: ['./canjear-vale.component.scss']
})
export class CanjearValeComponent implements OnInit {
  
  valeCanjeado: ValeCanjeado;
  totalNegocio: number;
  totalCanjeadoNegocio: number;
  descuento: number;
  
  qr: FormControl;
  total: FormControl;
  canjearValeForm: FormGroup;

  mensaje = '';
  error = false;
 
  constructor(
    private valeService: ValeService,
    private negocioService: NegocioService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.qr = new FormControl('', [
      Validators.required,
      Validators.minLength(20), 
      Validators.maxLength(20)
    ]);
    this.total = new FormControl('', [
      Validators.required,
      Validators.pattern("^[0-9]+(?:\.[0-9]{1,2})?$") ///^\d+(\.\d{1,2})?$/")
    ]);

    this.canjearValeForm = this.formBuilder.group({
      qr: this.qr,
      total: this.total,
    });
  }

  obtenerTotalNegocio(): void {
    const emailNegocio = this.authService.getEmail();
    this.negocioService.obtenerTotalCanjeadoNegocio(emailNegocio).subscribe(
      (data) => {
        this.totalCanjeadoNegocio = data;
      },
      (err) => {
        this.error = true;
      }
    );

    this.negocioService.getNegocio(emailNegocio).subscribe(
      (data) => {
        this.totalNegocio = data;
      },
      (err) => {
        this.error = true;
      }
    );
  }

  onCanjearVale(): void {
    this.valeCanjeado = new ValeCanjeado();
    this.valeCanjeado.qr = this.qr.value;
    this.valeCanjeado.emailNegocio = this.authService.getEmail();
    this.valeCanjeado.total = this.total.value;

    this.valeService.canjearValue(this.valeCanjeado).subscribe(
      (data) => {
        this.mensaje = 'Vale canjeado correctamente';
        this.descuento = data;
      },
      (err) => {
        this.error = true;
        this.mensaje = err.error;
      }
    );
  }
}
