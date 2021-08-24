import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isCollapsed = false;

  constructor(
    private auth : AuthService
  ) { }

  ngOnInit(): void {
  }

  loggedIn() : boolean {
    return this.auth.checkLogin();
  }

}
