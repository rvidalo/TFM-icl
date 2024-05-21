import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaNegociosAdminComponent } from './lista-negocios-admin.component';

describe('ListaNegociosAdminComponent', () => {
  let component: ListaNegociosAdminComponent;
  let fixture: ComponentFixture<ListaNegociosAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaNegociosAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaNegociosAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
