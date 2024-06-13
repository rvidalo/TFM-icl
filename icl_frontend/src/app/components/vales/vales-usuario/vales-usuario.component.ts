import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Vale } from 'src/app/models/vale';
import { ValeCanjeado } from 'src/app/models/vale-canjeado';
import { AuthService } from 'src/app/services/auth.service';
import { ValeService } from 'src/app/services/vale.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-vales-usuario',
  templateUrl: './vales-usuario.component.html',
  styleUrls: ['./vales-usuario.component.scss']
})
export class ValesUsuarioComponent implements OnInit {
  
  canjeadosUsuario: ValeCanjeado[];
  valeUsuario: Vale = new Vale();
  restante: number = 0;
  isLoading = true;

  dataSource = new MatTableDataSource<ValeCanjeado>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('qrModal', { static: true }) qrModalTemplate: TemplateRef<any>;
  displayedColumns: string[] = ['negocio', 'total', 'descuento', 'fecha'];

  private modalRef: MatDialogRef<any>;
 
  constructor(
    private valeService: ValeService,
    private authService: AuthService,
    private dialog: MatDialog
  ) {}

  async ngOnInit() {
    const emailDetalle = this.authService.getEmailDetalle();
    const emailToken = emailDetalle ? emailDetalle : this.authService.getEmail();

    try {
      this.valeUsuario = await this.valeService.getValeUsuario(emailToken);
      if (this.valeUsuario) {
        this.restante = parseInt(this.valeUsuario.valorTotal, 10) - this.valeUsuario.totalCanjeado;

        this.canjeadosUsuario = await this.valeService.getCanjeadosUsuario(emailToken);
        this.dataSource.data = this.canjeadosUsuario;
        this.dataSource.sort = this.sort;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
        this.dataSource.paginator = this.paginator;
        this.paginator.pageSize = 10;
      }
    } catch (err) {
      console.log(err);
    } finally {
      this.isLoading = false;
    }

    this.authService.setEmailDetalle("");
  }

  aplicarFiltro(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openQrModal(qrData: string): void {
    this.valeUsuario.qr = qrData;
    this.modalRef = this.dialog.open(this.qrModalTemplate);
  }

  closeQrModal(): void {
     if (this.modalRef) {
      this.modalRef.close();
    }
  }

  filtroPersonalizado(data: ValeCanjeado, filter: string): boolean {
    const searchString = filter.toLowerCase().trim();

    return (
      data.emailNegocio.toLowerCase().includes(searchString) ||
      data.total.toString().toLowerCase().includes(searchString) ||
      data.descuento.toString().toLowerCase().includes(searchString) ||
      data.fechaRegistro.toString().toLowerCase().includes(searchString)
    );
  }
}
