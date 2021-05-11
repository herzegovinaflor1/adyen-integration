import { TestBed } from '@angular/core/testing';

import { Payments.ListService } from './payments.list.service';

describe('Payments.ListService', () => {
  let service: Payments.ListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Payments.ListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
