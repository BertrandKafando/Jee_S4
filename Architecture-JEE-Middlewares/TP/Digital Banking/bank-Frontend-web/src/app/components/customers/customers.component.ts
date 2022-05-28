import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../Services/customer.service";
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AppDataState, DataStateEnum} from "../../data.state";
import { Customer } from '../../Model/customers.model';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {state} from "@angular/animations";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customer$!:Observable<AppDataState<Customer[]>>
  readonly DataStateEnum=DataStateEnum;
  frormGroup!:FormGroup
  constructor(private router:Router, private fb:FormBuilder,private customerService:CustomerService) { }

  ngOnInit(): void {

    this.frormGroup=this.fb.group(
      {
        keyword:[""],

      }
    );
    this.handleSearchCustomers();
  }

  onGetAllCustomers(){
   this.customer$= this.customerService.getCustomers().pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
    )

  }


  handleSearchCustomers() {
    let key=this.frormGroup.value.keyword;
    this.customer$=this.customerService.searchcustomers(key).pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))

    );
  }

  handledeletecustomer(p:Customer) {
    let conf=confirm("Are you sur ?")
    if(!conf) return
      this.customerService.deleteCustomer(p).subscribe(
        {
          next:resp=>{
            this.customer$=this.customer$.pipe(
              map(
                data=>{
                  let index=data.data?.indexOf(p);
                  data.data?.slice(index,1)
                  return data;
                }
              )
            );
          },
          error:err => {
            console.log(err);
          }
        }
      );
  }

  handledeletecustomerAccounts(p: Customer) {
    this.router.navigateByUrl("/customer-accounts/"+p.id,{state:p})
  }
}
