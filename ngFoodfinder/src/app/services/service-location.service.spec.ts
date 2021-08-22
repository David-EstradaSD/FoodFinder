import { TestBed } from '@angular/core/testing';

import { ServiceLocationService } from './service-location.service';

describe('ServiceLocationService', () => {
  let service: ServiceLocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceLocationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
