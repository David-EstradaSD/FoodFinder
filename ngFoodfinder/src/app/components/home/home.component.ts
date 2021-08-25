import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
}
