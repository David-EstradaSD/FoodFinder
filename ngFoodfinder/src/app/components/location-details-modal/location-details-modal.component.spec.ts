import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationDetailsModalComponent } from './location-details-modal.component';

describe('LocationDetailsModalComponent', () => {
  let component: LocationDetailsModalComponent;
  let fixture: ComponentFixture<LocationDetailsModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationDetailsModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationDetailsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
