import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanjearValeComponent } from './canjear-vale.component';

describe('CanjearValeComponent', () => {
  let component: CanjearValeComponent;
  let fixture: ComponentFixture<CanjearValeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CanjearValeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CanjearValeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
