import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private auth : AuthService,
    private router : Router
  ) { }

  ngOnInit(): void {
  }

  logout() {
    console.log('Logging out');
    this.auth.logout(); // this is the most simple function. No observable
    this.router.navigateByUrl('/home'); // just need to re-route to home page

  }

}
