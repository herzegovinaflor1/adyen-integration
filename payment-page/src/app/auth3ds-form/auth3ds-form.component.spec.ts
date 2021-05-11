import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Auth3dsFormComponent } from './auth3ds-form.component';

describe('Auth3dsFormComponent', () => {
  let component: Auth3dsFormComponent;
  let fixture: ComponentFixture<Auth3dsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Auth3dsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Auth3dsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
