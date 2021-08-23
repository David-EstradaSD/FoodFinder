import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarFooterComponent } from './navbar-footer.component';

describe('NavbarFooterComponent', () => {
  let component: NavbarFooterComponent;
  let fixture: ComponentFixture<NavbarFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
