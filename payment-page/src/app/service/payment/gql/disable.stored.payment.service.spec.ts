import { TestBed } from '@angular/core/testing';

import { DisableStoredPaymentService } from './disable.stored.payment.service';

describe('Disable.Stored.PaymentService', () => {
  let service: DisableStoredPaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisableStoredPaymentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
