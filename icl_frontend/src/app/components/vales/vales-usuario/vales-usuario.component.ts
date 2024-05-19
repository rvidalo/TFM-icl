import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Vale } from 'src/app/models/vale';
import { ValeCanjeado } from 'src/app/models/vale-canjeado';
import { AuthService } from 'src/app/services/auth.service';
import { ValeService } from 'src/app/services/vale.service';

@Component({
  selector: 'app-vales-usuario',
  templateUrl: './vales-usuario.component.html',
  styleUrls: ['./vales-usuario.component.scss']
})
export class ValesUsuarioComponent implements OnInit {
  
  canjeadosUsuario: ValeCanjeado[];
  valeUsuario: Vale = new Vale();
  totalDescuentos: number = 0;
  restante: number = 0;

  dataSource = new MatTableDataSource<ValeCanjeado>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['negocio', 'total', 'descuento', 'fecha'];
 
  constructor(
    private valeService: ValeService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {

    const emailToken = this.authService.getEmail();

    this.valeService.getValeUsuario(emailToken).subscribe(
      (data) => {
        this.valeUsuario = data;
      },
      (err) => {
        console.log(err);
      }
    );

    if (this.valeUsuario == null){
      return;
    }
    
    this.valeService.getCanjeadosUsuario(emailToken).subscribe(
      (data) => {
        this.canjeadosUsuario = data;
        this.dataSource.data = data;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
        this.paginator.pageSize = 10;
      },
      (err) => {
        console.log(err);
      }
    );
    this.totalDescuentos = this.valeService.calcularTotalDescuentos(this.canjeadosUsuario);
    console.log(parseInt(this.valeUsuario.valorTotal, 10) );
    console.log(this.totalDescuentos );
    this.restante = parseInt(this.valeUsuario.valorTotal, 10) - this.totalDescuentos;
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
      data.emailNegocio.toLowerCase().includes(searchString) ||
      data.total.toString().toLowerCase().includes(searchString) ||
      data.descuento.toString().toLowerCase().includes(searchString) ||
      data.fechaRegistro.toString().toLowerCase().includes(searchString)
    );
  }
}
