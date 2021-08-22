import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Recipient } from '../models/recipient';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipientService {

  private recipients : Recipient[] = [];
  private url = environment.baseUrl + 'api/users/recipients';

  constructor(
    private http : HttpClient,
    private auth : AuthService
  ) { }

  public index() : Observable<Recipient[]> {
    return this.http.get<Recipient[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('Error getting Recipient list');
      })
    );
  }

  public showById(recipientId : any) : Observable<Recipient> {
    return this.http.get<Recipient>(this.url + "/" + recipientId, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('RecipientService.showById(): error getting recipient id' + recipientId);
      })
    );
  }

  public update(recipient : Recipient) {
    return this.http.put<Recipient>(this.url, recipient, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('RecipientService.update(): error updating recipient of id' + recipient.id);
      })
    );
  }

  public destroy(id : number) {
    return this.http.delete<Recipient>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('RecipientService.destroy(): error deleting recipient of id' + id);
      })
    );
  }

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
}

