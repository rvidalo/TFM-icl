import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValesUsuarioComponent } from './vales-usuario.component';

describe('ValesUsuarioComponent', () => {
  let component: ValesUsuarioComponent;
  let fixture: ComponentFixture<ValesUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValesUsuarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValesUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
