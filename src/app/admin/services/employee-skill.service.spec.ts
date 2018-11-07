import { TestBed } from '@angular/core/testing';

import { EmployeeSkillService } from './employee-skill.service';

describe('EmployeeSkillService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeeSkillService = TestBed.get(EmployeeSkillService);
    expect(service).toBeTruthy();
  });
});
