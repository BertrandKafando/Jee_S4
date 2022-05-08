import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormstudentComponent } from './formstudent.component';

describe('FormstudentComponent', () => {
  let component: FormstudentComponent;
  let fixture: ComponentFixture<FormstudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormstudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormstudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
