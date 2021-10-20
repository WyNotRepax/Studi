import { TestBed } from '@angular/core/testing';

import { ObjectPlacerService } from './object-placer.service';

describe('ObjectPlacerService', () => {
  let service: ObjectPlacerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObjectPlacerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
