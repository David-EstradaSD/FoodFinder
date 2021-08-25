import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  newUser: User = new User();
  newAddress: Address = new Address();
  combinedAddressUser: Object = {};
  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;
  pauseOnFocus = true;

  images = ['../../assets/casaBonita.jpg', '../../assets/baptistChurch.jpg', '../../assets/havenOfHope.jpg'];

  @ViewChild('carousel', {static : true}) carousel: NgbCarousel;

  constructor(
    private auth: AuthService,
    private router: Router // We need this to reroute on success/fail

  ) { }

  ngOnInit(): void {
  }

  routeToRecipient = function() {
    this.router.navigateByUrl('/recipient-registration')
  }

  routeToDonor = function() {
    this.router.navigateByUrl('/donor-registration')
  }

  togglePaused() {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
  }

  onSlide(slideEvent: NgbSlideEvent) {
    if (this.unpauseOnArrow && slideEvent.paused &&
      (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)) {
      this.togglePaused();
    }
    if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
      this.togglePaused();
    }
  }
}
