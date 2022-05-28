import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Customer} from "../Model/customers.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http:HttpClient) {

  }

  public getCustomers():Observable<Customer[]>{
    console.log("ok")
    return this.http.get<Customer[]>(environment.host+"/customers")

  }

  public searchcustomers(key:String):Observable<Array<Customer>>{
    return this.http.get<Customer[]>(environment.host+"/customers/search?keyword="+key);
  }

  public saveCustomer(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>(environment.host+"/customers",customer);
  }

  public deleteCustomer(cutomer:Customer):Observable<Customer>{
    return  this.http.delete<Customer>(environment.host+"/customers/"+cutomer.id);
  }

}
