import { TestBed } from '@angular/core/testing';

import { AdminGauardGuard } from './admin-gauard.guard';

describe('AdminGauardGuard', () => {
  let guard: AdminGauardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminGauardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
