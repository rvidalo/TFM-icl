import { Component, OnInit, ViewChild } from '@angular/core';
import { Negocio } from 'src/app/models/negocio';
import { NegocioService } from 'src/app/services/negocio.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-lista-negocios-admin',
  templateUrl: './lista-negocios-admin.component.html',
  styleUrls: ['./lista-negocios-admin.component.scss']
})
export class ListaNegociosAdminComponent implements OnInit {

  dataSource = new MatTableDataSource<Negocio>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['nombre', 'direccion', 'descripcion', 'email', 'totalCanjeado'];

  constructor(
    private negocioService: NegocioService, 
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.obtenerNegocios();
  }

  obtenerNegocios(): void {
    this.negocioService.getNegocios().subscribe(
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

  onRowClicked(row: Negocio) {
    this.authService.setEmailDetalle(row.email);
    this.router.navigate(['/vales-negocio']);
  }
  
  filtroPersonalizado(data: Negocio, filter: string): boolean {
    const searchString = filter.toLowerCase().trim();

    return (
      data.nombre.toLowerCase().includes(searchString) ||
      data.direccion.toLowerCase().includes(searchString) ||
      data.tipo.descripcion.toLowerCase().includes(searchString) ||
      data.email.toLowerCase().includes(searchString) || 
      data.totalCanjeado.toString().toLowerCase().includes(searchString) 
    );
  }
}
