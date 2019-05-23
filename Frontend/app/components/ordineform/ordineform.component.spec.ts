import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdineformComponent } from './ordineform.component';

describe('OrdineformComponent', () => {
  let component: OrdineformComponent;
  let fixture: ComponentFixture<OrdineformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdineformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdineformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
