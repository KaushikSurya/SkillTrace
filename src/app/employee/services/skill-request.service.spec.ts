import { TestBed, inject } from '@angular/core/testing';

import { SkillRequestService } from './skill-request.service';

describe('SkillRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SkillRequestService]
    });
  });

  it('should be created', inject([SkillRequestService], (service: SkillRequestService) => {
    expect(service).toBeTruthy();
  }));
});
