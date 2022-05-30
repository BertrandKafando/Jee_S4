import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {Customer} from "../../Model/customers.model";
import {environment} from "../../../environments/environment";
import {Token} from "../../Model/login";
import {User} from "../../Model/user.model";


@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  userSubject : Subject<User|undefined> = new Subject();
  user ?: User;
  refresh_token="";
  access_token="";


  constructor( private http:HttpClient) {
    this.access_token = localStorage.getItem("access_token")? localStorage.getItem("access_token")+"":"";
    this.refresh_token = localStorage.getItem("refresh_token")? localStorage.getItem("refresh_token")+"":"";
  }


  public loginRequest( username:string, password:string){
    const body = new HttpParams()
      .set('username', username)
      .set('password', password);

    return new Promise<boolean>( (resolve, reject)=>{
      this.http.post<any>(environment.host+"/login", body.toString(),
        {
          headers: new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
        }).subscribe({
        next:res=>{
          this.access_token = res.access_token
          this.refresh_token = res.refresh_token
          localStorage.setItem("access_token", this.access_token)
          localStorage.setItem("refresh_token", this.refresh_token)
          this.getUser()
          resolve(true)
        },
        error: err=>{
          this.access_token = this.refresh_token = ""
          reject(false)
        }
      })
    })
  }


  public getUser(){
    if( this.access_token.length==0 ) return;
    const authorizationHeader = "Bearer "+this.access_token
    this.http.get<User>(environment.host+"/profile", { headers: {
        "Authorization": authorizationHeader
      } }).subscribe({
      next:res=>{
        this.user = res;
        this.userSubject.next(res)
      },
      error: err=>{
        console.error(err.message);
        this.refreshToken()
      }
    })
  }

  public refreshToken(){
    const authorizationHeader = "Bearer "+this.refresh_token
    return this.http.get<any>(environment.host+"/refresh", { headers: {
        "Authorization": authorizationHeader
      } })
  }

  public logout(){
    this.access_token = this.refresh_token = ""
    localStorage.removeItem("access_token")
    localStorage.removeItem("refresh_token")
    this.user= undefined;
    this.userSubject.next(undefined)
  }




}
