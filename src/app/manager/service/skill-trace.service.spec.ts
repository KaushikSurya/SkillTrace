import { TestBed, inject } from '@angular/core/testing';

import { SkillTraceService } from './skill-trace.service';

describe('SkillTraceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SkillTraceService]
    });
  });

  it('should be created', inject([SkillTraceService], (service: SkillTraceService) => {
    expect(service).toBeTruthy();
  }));
});
