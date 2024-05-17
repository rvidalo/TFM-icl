import { Component, OnInit, ViewChild } from '@angular/core';
import { Negocio } from 'src/app/models/negocio';
import { NegocioService } from 'src/app/services/negocio.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-lista-negocios',
  templateUrl: './lista-negocios.component.html',
  styleUrls: ['./lista-negocios.component.scss']
})
export class ListaNegociosComponent implements OnInit {

  dataSource = new MatTableDataSource<Negocio>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['nombre', 'direccion', 'tipo', 'email'];

  constructor(private negocioService: NegocioService) { }

  ngOnInit(): void {
    this.obtenerNegocios();
  }

  obtenerNegocios(): void {
    this.negocioService.getNegocios().subscribe(
      (data) => {
        this.dataSource.data = data;
        this.paginator.pageSize = 15;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filterPredicate = this.filtroPersonalizado.bind(this);
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

  filtroPersonalizado(data: Negocio, filter: string): boolean {
    const searchString = filter.toLowerCase().trim();

    // Verifica si el filtro coincide con cualquier propiedad de la fila de datos
    return (
      data.nombre.toLowerCase().includes(searchString) ||
      data.direccion.toLowerCase().includes(searchString) ||
      data.tipo.descripcion.toLowerCase().includes(searchString) ||
      data.email.toLowerCase().includes(searchString)
    );
  }
}
