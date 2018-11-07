import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerAnalysisComponent } from './manager-analysis.component';

describe('ManagerAnalysisComponent', () => {
  let component: ManagerAnalysisComponent;
  let fixture: ComponentFixture<ManagerAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
