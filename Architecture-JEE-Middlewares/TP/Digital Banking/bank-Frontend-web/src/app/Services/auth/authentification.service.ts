import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../../Model/customers.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(private http:HttpClient) {

  }


  public login(username:any,password:any):Observable<HttpHeaders> {

    const body = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post<HttpHeaders>(environment.host+'/login',
      body.toString(),
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded')
      }
    );

  }




}
