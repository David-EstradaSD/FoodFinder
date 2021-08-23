import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipientComponent } from './recipient.component';

describe('RecipientComponent', () => {
  let component: RecipientComponent;
  let fixture: ComponentFixture<RecipientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
