import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpCourseComponent } from './emp-course.component';

describe('EmpCourseComponent', () => {
  let component: EmpCourseComponent;
  let fixture: ComponentFixture<EmpCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
