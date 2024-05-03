import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudValeComponent } from './solicitud-vale.component';

describe('SolicitudValeComponent', () => {
  let component: SolicitudValeComponent;
  let fixture: ComponentFixture<SolicitudValeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudValeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudValeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
