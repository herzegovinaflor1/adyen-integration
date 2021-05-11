import { TestBed } from '@angular/core/testing';

import { StoredPaymentsService } from './stored-payments.service';

describe('StoredPaymentsService', () => {
  let service: StoredPaymentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StoredPaymentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
