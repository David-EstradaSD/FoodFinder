import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: User = new User();
  newAddress: Address = new Address();
  combinedAddressUser: Object = {};

  constructor(
    private auth: AuthService,
    private router: Router // We need this to reroute on success/fail

  ) { }

  ngOnInit(): void {
  }

  register(userIn: User, addressIn: Address) {
    this.combinedAddressUser = { "user": userIn, "address": addressIn};
    console.log("In register() with user name: " + userIn.username + " password: " + userIn.password + " email: " + userIn.email);
    this.auth.register(this.combinedAddressUser).subscribe(
      userOut => {
        console.log('RegisterComponent.register(): user registered.');
        this.auth.login(userIn.username, userIn.password).subscribe( // Autologin if user registration successful using the given cleartext
          loggedInUser => {
            console.log('RegisterComponent.register(): user logged in successfully.');
              this.router.navigateByUrl('/foodfinder');
          },
          badJuju => {
            console.log('RegisterComponent.register(): user login failed.');
              this.router.navigateByUrl('/login');
          }
        )
      },
      fail => {
        console.log('RegisterComponent.register(): user registration failed.');
              this.router.navigateByUrl('/login');
      }
    )
  }
}
