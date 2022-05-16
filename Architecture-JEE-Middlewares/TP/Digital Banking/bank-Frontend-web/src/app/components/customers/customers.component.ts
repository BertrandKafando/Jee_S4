import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../Services/customer.service";
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AppDataState, DataStateEnum} from "../../data.state";
import { Customer } from '../Model/customers.model';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customer$:Observable<AppDataState<Customer[]>>|undefined
  readonly DataStateEnum=DataStateEnum;
  constructor(private customerService:CustomerService) { }

  ngOnInit(): void {
    this.onGetAllCustomers();
  }

  onGetAllCustomers(){
   this.customer$= this.customerService.getCustomers().pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
    )

  }


}
