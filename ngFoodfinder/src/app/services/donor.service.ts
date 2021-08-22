import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Donor } from '../models/donor';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DonorService {

  private donors : Donor[] = [];
  private url = environment.baseUrl + 'api/donors';

  constructor(
    private http : HttpClient,
    private auth : AuthService
     ) { }

  public index(category : string) : Observable<Donor[]> {
    return this.http.get<Donor[]>(this.url + '/' + category, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('Error getting donor list by category:' + category);
      })
    );
  }

  public update(donor : Donor) {
    return this.http.put<Donor>(this.url + '/update/' + donor.id, this.getHttpOptions()).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('error updating donor with id' + donor.id);
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

