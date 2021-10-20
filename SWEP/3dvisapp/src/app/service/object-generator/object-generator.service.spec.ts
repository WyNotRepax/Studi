import { TestBed } from '@angular/core/testing';

import { ObjectGeneratorService } from './object-generator.service';

describe('ObjectGeneratorService', () => {
  let service: ObjectGeneratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObjectGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
