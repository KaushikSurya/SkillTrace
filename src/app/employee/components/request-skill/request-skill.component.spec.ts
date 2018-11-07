import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestSkillComponent } from './request-skill.component';

describe('RequestSkillComponent', () => {
  let component: RequestSkillComponent;
  let fixture: ComponentFixture<RequestSkillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestSkillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
