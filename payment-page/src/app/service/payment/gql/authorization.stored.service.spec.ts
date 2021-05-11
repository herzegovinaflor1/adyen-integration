import { TestBed } from '@angular/core/testing';

import { AuthorizationStoredService } from './authorization.stored.service';

describe('Authorization.StoredService', () => {
  let service: AuthorizationStoredService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorizationStoredService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
