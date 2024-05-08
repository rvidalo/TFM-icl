import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtroNegocios'
})
export class FiltroNegociosPipe implements PipeTransform {

  transform(items: any[], searchText: string, keyToSearch: string): any[] {
    if (!items || !searchText) {
      return items;
    }

    searchText = searchText.toLowerCase().trim();

    return items.filter(item => {
      return item[keyToSearch].toLowerCase().includes(searchText);
    });
  }
}