import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private urlGet = environment.baseUrl + 'api/addresses';                                                // depends on whether we're using it locally or deployed
  private urlPutDonor = environment.baseUrl + 'api/address/donor/addressId';
  private urlPutRecipient = environment.baseUrl + 'api/address/recipient/addressId';

  constructor(private http: HttpClient, private auth: AuthService) { }


index(): Observable<Address[]> {
  return this.http.get<Address[]>(this.urlGet, this.getHttpOptions()).pipe(
  catchError((err: any) => {
    console.error('AddressService.index(): error retrieving address list');
    return throwError(err);
  })
  );
}

updateDonor(address: Address) {
  const httpOptions = {
    headers: {
      'Content-type': 'application/json'
    }
  }
  return this.http.put<Address>(this.urlPutDonor, address, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.error(err);
      return throwError(
        'error updating address' + err
      );
    }
    )
  );
}

updateRecipient(address: Address) {
  const httpOptions = {
    headers: {
      'Content-type': 'application/json'
    }
  }
  return this.http.put<Address>(this.urlPutRecipient, address, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.error(err);
      return throwError(
        'error updating address' + err
      );
    }
    )
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


