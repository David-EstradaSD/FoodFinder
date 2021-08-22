import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl + 'api/users';                                      // depends on whether we're using it locally or deployed

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type' : 'application/json',
        'X-Requested-With' : 'XMLHttpRequest',
        'Authorization' : `Basic ${credentials}`
      }),
    };
    return httpOptions;
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.error('UserService.index(): error retrieving address list');
      return throwError(err);
    })
    );
  }

  getUserByUsername(user: User, username: string) {
    return this.http.get<User>(this.baseUrl + username, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.error('UserService.getUserByUsername: error retrieving user');
      return throwError(err);
    })
    );
  }

  updateUser(user: User, username: string) {
    return this.http.put<User>(this.baseUrl + username, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          'error updating user' + err);
      })
    );
  }

  delete(username: string) {
    return this.http.delete<User>(this.baseUrl + username, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('Error deleting user');
        return throwError(err);
      })
      );
  }

  disableUser(user: User, username: string) {
    return this.http.put<User>(this.baseUrl + "disable" + username, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          'error disabling user' + err);
      })
    );
  }

  enableUser(user: User, username: string) {
    return this.http.put<User>(this.baseUrl + "enable" + username, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          'error enabling user' + err);
      })
    );
  }

}
