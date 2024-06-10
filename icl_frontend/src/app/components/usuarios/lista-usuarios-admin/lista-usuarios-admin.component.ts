import { Component, OnInit, ViewChild } from '@angular/core';
import { Negocio } from 'src/app/models/negocio';
import { NegocioService } from 'src/app/services/negocio.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { Vale } from 'src/app/models/vale';
import { ValeService } from 'src/app/services/vale.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-lista-usuarios-admin',
  templateUrl: './lista-usuarios-admin.component.html',
  styleUrls: ['./lista-usuarios-admin.component.scss']
})
export class ListaUsuariosAdminComponent implements OnInit {

  dataSource = new MatTableDataSource<Vale>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['nombre', 'email', 'codigo', 'fechaLimite', 'totalCanjeado'];

  constructor(
    private valeService: ValeService, 
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.obtenerVales();
  }

  obtenerVales(): void {
    this.valeService.getVales().subscribe(
      (data) => {
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
        this.paginator.pageSize = 10;
      },
      (error) => {
        console.error('Error al obtener los negocios:', error);
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

  onRowClicked(row: Vale) {
    this.authService.setEmailDetalle(row.usuario.email);
    this.router.navigate(['/vales-usuario']);
  }

  filtroPersonalizado(data: Vale, filter: string): boolean {
    const searchString = filter.toLowerCase().trim();

    return (
      data.usuario.nombre.toLowerCase().includes(searchString) ||
      data.usuario.apellidos.toLowerCase().includes(searchString) ||
      data.usuario.email.toLowerCase().includes(searchString) ||
      data.qr.toLowerCase().includes(searchString) ||
      data.fechaLimite.toString().toLowerCase().includes(searchString) || 
      data.totalCanjeado.toString().toLowerCase().includes(searchString) 
    );
  }
}
