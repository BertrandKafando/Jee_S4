import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Customer} from "../components/Model/customers.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
host=environment.host;
  constructor(private http:HttpClient) {

  }

  public getCustomers():Observable<Customer[]>{
    console.log("ok")
    return this.http.get<Customer[]>(this.host+"/customers")

  }


}
