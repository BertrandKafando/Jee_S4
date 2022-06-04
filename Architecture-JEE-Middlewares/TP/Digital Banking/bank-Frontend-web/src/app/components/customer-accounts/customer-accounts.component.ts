import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../Model/customers.model";
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AppDataState, DataStateEnum} from "../../data.state";
import {Account} from "../../Model/account.customer";
import {CustomerService} from "../../Services/customer.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {
  customerId!:String
  customer!:Customer
  accounts$!:Observable<AppDataState<Account[]>>
  readonly DataStateEnum=DataStateEnum;
  constructor(private  route: ActivatedRoute,private router:Router,private customerService:CustomerService) {
   this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.customerId=this.route.snapshot.params['id']
    this.onGetCustomersAccounts();

  }

  onGetCustomersAccounts(){
    this.accounts$= this.customerService.getcustomersAccounts(this.customer).pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data }) ,
      ),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
    )

  }


  handledeletecustomerAccounts(p: Account) {
    this.router.navigateByUrl("/accounts-show/"+p.id,{state:p})
  }

}
