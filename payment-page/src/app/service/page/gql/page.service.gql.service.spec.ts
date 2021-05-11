import { TestBed } from '@angular/core/testing';

import { PageServiceGqlService } from './page.service.gql.service';

describe('PageServiceGqlService', () => {
  let service: PageServiceGqlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PageServiceGqlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
