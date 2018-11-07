import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillReportsComponent } from './skill-reports.component';

describe('SkillReportsComponent', () => {
  let component: SkillReportsComponent;
  let fixture: ComponentFixture<SkillReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
