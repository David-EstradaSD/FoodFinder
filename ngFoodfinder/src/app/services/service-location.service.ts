import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ServiceLocation } from '../models/service-location';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceLocationService {
  private url = environment.baseUrl + 'api/';

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) { }


  /**
   * index() makes a defensive copy of array to return to caller
   */
   index(): Observable<ServiceLocation[]> {
    return this.http.get<ServiceLocation[]>(`${this.url}service-locations`).pipe(
      catchError((err: any) => {
        console.error('serviceLocationService.index() error');
        return throwError(err);
      })

    );
  }

//   /**
//    * create
//    */
  public create(servLoc: ServiceLocation) {

    return this.http.post<ServiceLocation>(`${this.url}service-locations`, servLoc, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("YOU BROKE SOMETHING IN create()");
        })
      );
  }

// //   /**
// //    * update
// //  */
  public update(servLoc: ServiceLocation) {
    return this.http.put<ServiceLocation>(`${this.url}service-locations/${servLoc.id}`, servLoc, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("YOU BROKE SOMETHING IN update()");
      })
    );
  }

// //   /**
// //    * destroy
// //    */
  public destroy(id: number) {
    return this.http.delete(this.url + "service-locations/" + id, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("YOU BROKE SOMETHING IN destroy()");
      })
    )
  }

//   /**
//    * show
//    */
  public show(id: number) {
    return this.http.get<ServiceLocation>(`${this.url}service-locations/${id}`, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("YOU BROKE SOMETHING IN show()");
      })
    )
  }

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders( {
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      }),
    }
    return httpOptions;
  }
}
