import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Scanv2Component } from './scanv2.component';

describe('Scanv2Component', () => {
  let component: Scanv2Component;
  let fixture: ComponentFixture<Scanv2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Scanv2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Scanv2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
