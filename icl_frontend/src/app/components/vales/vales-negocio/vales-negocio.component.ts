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
  totalDescuentos: number = 0;
  solicitudRealizada: boolean = false;
  restante: number = 0;
  negocio: PerfilNegocio = new PerfilNegocio();
  
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

    const emailToken = this.authService.getEmail();
    
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
        this.canjeadosNegocio = data;
        this.dataSource.data = data;
        this.dataSource.sort = this.sort;
        this.totalDescuentos = this.valeService.calcularTotalDescuentos(this.canjeadosNegocio);
        this.restante = this.negocio.valorTotal - this.totalDescuentos;
        this.dataSource.paginator = this.paginator;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
        this.paginator.pageSize = 10;
      },
      (err) => {
        console.log(err);
      }
    );
    
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
