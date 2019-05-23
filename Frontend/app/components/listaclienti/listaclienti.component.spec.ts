import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaClientiComponent } from './listaclienti.component';

describe('ListaclientiComponent', () => {
  let component: ListaClientiComponent;
  let fixture: ComponentFixture<ListaClientiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaClientiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaClientiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
