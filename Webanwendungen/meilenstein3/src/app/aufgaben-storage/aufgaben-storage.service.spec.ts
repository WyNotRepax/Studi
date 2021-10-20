import { TestBed } from '@angular/core/testing';

import { AufgabenStorageService } from './aufgaben-storage.service';

describe('AufgabenStorageService', () => {
  let service: AufgabenStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AufgabenStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
