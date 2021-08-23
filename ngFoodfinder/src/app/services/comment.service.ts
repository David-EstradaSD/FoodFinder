import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root',
})
export class CommentService {

  private urlGet = environment.baseUrl + 'api/service';
  private baseUrl = environment.baseUrl + 'api/service';

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

  // index(): Observable<Comment[]> {
  // return this.http.get<Comment[]>(this.urlGet, this.getHttpOptions()).pipe(
  //   catchError((err: any) => {
  //     console.error('CommentService.index(): error in index comment')
  //   })
  // );

  // }

  create(comment: Comment, serviceLocationId: number): Observable<Comment> {
    return this.http
      .post<Comment>(
        `${this.baseUrl}${serviceLocationId}/comment`,
        comment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error('CommentService.create(): error in create comment');
          return throwError(err);
        })
      );
  }

  // update(comment: Comment): Observable<Comment> {
  //   return this.http
  //     .put<Comment>(
  //       `${this.baseUrl}/${comment.id}`,
  //       comment,
  //       this.getHttpOptions()
  //     )
  //     .pipe(
  //       catchError((err: any) => {
  //         console.error('CommentService.update(): error in update comment');
  //         return throwError(err);
  //       })
  //     );
  // }

  delete(id: number): Observable<void> {
    return this.http
      .delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error('CommentService.disable(): error in delete comment');
          return throwError(err);
        })
      );
  }
}
