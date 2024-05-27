import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PerfilNegocio } from 'src/app/models/perfil-negocio';
import { ValeCanjeado } from 'src/app/models/vale-canjeado';
import { AuthService } from 'src/app/services/auth.service';
import { NegocioService } from 'src/app/services/negocio.service';
import { ValeService } from 'src/app/services/vale.service';

@Component({
  selector: 'app-vales-negocio',
  templateUrl: './vales-negocio.component.html',
  styleUrls: ['./vales-negocio.component.scss']
})
export class ValesNegocioComponent implements OnInit {
  
  canjeadosNegocio: ValeCanjeado[];
  solicitudRealizada: boolean = false;
  restante: number = 0;
  negocio: PerfilNegocio = new PerfilNegocio();
  isLoading = true;
  
  dataSource = new MatTableDataSource<ValeCanjeado>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['vale', 'total', 'descuento', 'fecha'];

  constructor(
    private valeService: ValeService,
    private authService: AuthService,
    private negocioService: NegocioService,
  ) {}

  ngOnInit(): void {

    const emailDetalle = this.authService.getEmailDetalle();
    const emailToken = emailDetalle ? emailDetalle : this.authService.getEmail();

    this.negocioService.getPerfilNegocio(emailToken).subscribe(
      (data) => {
        console.log('Negocio');
        console.log(data);
        this.negocio = data;
      },
      (err) => {
        console.log(err);
      }
    );

    this.valeService.getCanjeadosNegocio(emailToken).subscribe(
      (data) => {
        console.log("vales negocio");
        console.log(data);
        this.canjeadosNegocio = data;
        this.dataSource.data = data;
        this.dataSource.sort = this.sort;
        this.restante = this.negocio.valorTotal - this.negocio.totalCanjeado;
        this.dataSource.paginator = this.paginator;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
        this.isLoading = false;
        this.paginator.pageSize = 15;
      },
      (err) => {
        console.log(err);
        this.isLoading = false;
      }
    );
    this.authService.setEmailDetalle("");
  }
  
  aplicarFiltro(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  filtroPersonalizado(data: ValeCanjeado, filter: string): boolean {
    const searchString = filter.toLowerCase().trim();

    return (
      data.qr.toLowerCase().includes(searchString) ||
      data.total.toString().toLowerCase().includes(searchString) ||
      data.descuento.toString().toLowerCase().includes(searchString) ||
      data.fechaRegistro.toString().toLowerCase().includes(searchString)
    );
  }

}
