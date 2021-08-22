import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodFinderComponent } from './food-finder.component';

describe('FoodFinderComponent', () => {
  let component: FoodFinderComponent;
  let fixture: ComponentFixture<FoodFinderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodFinderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodFinderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
