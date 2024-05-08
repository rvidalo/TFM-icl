import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValesNegocioComponent } from './vales-negocio.component';

describe('ValesNegocioComponent', () => {
  let component: ValesNegocioComponent;
  let fixture: ComponentFixture<ValesNegocioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValesNegocioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValesNegocioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
