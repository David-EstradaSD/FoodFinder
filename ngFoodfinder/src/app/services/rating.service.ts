import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Rating } from '../models/rating';
import { Recipient } from '../models/recipient';

@Injectable({
  providedIn: 'root',
})
export class RatingService {
  private baseUrl = environment.baseUrl + 'api/ratings';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
        Authorization: `Basic ${credentials}`,
      }),
    };
    return httpOptions;
  }

  create(
    rating: Rating,
    serviceLocationId: number,
    recipientId: number
  ): Observable<Rating> {
    return this.http
      .post<Rating>(
        `${this.baseUrl}/recipient/${recipientId}/serviceLocation/${serviceLocationId}`,
        rating,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error('RatingService.create(): error in create rating');
          return throwError(err);
        })
      );
  }

  update(
    rating: Rating,
    serviceLocationId: number,
    recipientId: number
  ): Observable<Rating> {
    return this.http
      .put<Rating>(
        `${this.baseUrl}/recipient/${recipientId}/serviceLocation/${serviceLocationId}`,
        rating,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error('RatingService.update(): error in update rating');
          return throwError(err);
        })
      );
  }

  delete(serviceLocationId: number, recipientId: number) {
    return this.http
      .delete<void>(
        `${this.baseUrl}/recipient/${recipientId}/serviceLocation/${serviceLocationId}}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error('CommentService.disable(): error in delete comment');
          return throwError(err);
        })
      );
  }
}
