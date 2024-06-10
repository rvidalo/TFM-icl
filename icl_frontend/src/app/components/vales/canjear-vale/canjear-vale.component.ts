import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PerfilNegocio } from 'src/app/models/perfil-negocio';
import { ValeCanjeado } from 'src/app/models/vale-canjeado';
import { AuthService } from 'src/app/services/auth.service';
import { NegocioService } from 'src/app/services/negocio.service';
import { ValeService } from 'src/app/services/vale.service';
import QrScanner from 'qr-scanner';

@Component({
  selector: 'app-canjear-vale',
  templateUrl: './canjear-vale.component.html',
  styleUrls: ['./canjear-vale.component.scss']
})
export class CanjearValeComponent implements OnInit {
  
  valeCanjeado: ValeCanjeado;
  descuento: number;
  negocio: PerfilNegocio;
  restante: number = 0;
  isLoading = true;
  @ViewChild('video', { static: true }) videoElement: ElementRef;
  qrScanner: any;
  
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
      Validators.pattern("^(?!0$)(?!0\\.0$)(?!0\\.00$)[0-9]+(?:\\.[0-9]{1,2})?$") //"^[0-9]+(?:\.[0-9]{1,2})?$")
    ]);

    this.canjearValeForm = this.formBuilder.group({
      qr: this.qr,
      total: this.total,
    });

    const emailNegocio = this.authService.getEmail();

    this.negocioService.getPerfilNegocio(emailNegocio).subscribe(
      (data) => {
        this.negocio = data
        this.restante = this.negocio.valorTotal - this.negocio.totalCanjeado;
        this.isLoading = false;
      },
      (err) => {
        this.error = true;
        console.log(err);
        this.isLoading = false;
      }
    );

    // Inicializar QR Scanner
    this.qrScanner = new QrScanner(
      this.videoElement.nativeElement,
      (result: string) => this.onCodeResult(result),
      /*{
         highlightScanRegion: true,
        highlightCodeOutline: true, 
      } */
    );

    this.qrScanner.start().catch((error: any) => {
      console.error('Error al iniciar el escáner QR:', error);
    });
    
  }

  onCodeResult(decodedText: string): void {
    this.qr.setValue(decodedText);
  }

  onCanjearVale(): void {
    this.valeCanjeado = new ValeCanjeado();
    this.valeCanjeado.qr = this.qr.value;
    this.valeCanjeado.emailNegocio = this.authService.getEmail();
    this.valeCanjeado.total = this.total.value;

    this.valeService.canjearVale(this.valeCanjeado).subscribe(
      (data) => {
        this.mensaje = 'Vale canjeado correctamente';
        this.descuento = data;
        if(this.descuento == 0){
          this.valeCanjeado = null;
        }
      },
      (err) => {
        this.error = true;
        this.mensaje = err.error;
      }
    );
  }
}
