import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../../Model/account.model";
import {AccountsService} from "../../Services/accounts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../Model/customers.model";
import {Account} from "../../Model/account.customer";

@Component({
  selector: 'app-account-show',
  templateUrl: './account-show.component.html',
  styleUrls: ['./account-show.component.css']
})
export class AccountShowComponent implements OnInit {
  formGroupAccount!:FormGroup
  operationForm!:FormGroup
  currentPage:number=0;
  pagesize:number=5;
  accountObservable!:Observable<AccountDetails>
  errorMessage!:String
  account!:Account
  accountId!:string

  constructor(private fb:FormBuilder,private accountService:AccountsService,private  route: ActivatedRoute,private router:Router) {
    this.account=this.router.getCurrentNavigation()?.extras.state as Account;
  }

  ngOnInit(): void {
    this.accountId=this.route.snapshot.params['id']
    this.handleSearchAccount();
    this.formGroupAccount=this.fb.group(
      {
        accountId:[""],

      }
    );
    this.operationForm=this.fb.group(
      {
        operationType:this.fb.control(null),
        amount:this.fb.control(null),
        description:this.fb.control(null),
        accountDestination:this.fb.control(null),
      }
    )
  }

  handleSearchAccount(){
    this.accountObservable= this.accountService.getAccount(this.accountId,this.currentPage,this.pagesize).pipe(
      catchError (
        err => {
          this.errorMessage=err.message;
          return throwError(err);
        }
      )
    );

  }
  gotoPage(page:number){
    this.currentPage=page;
    this.handleSearchAccount();
  }


  handleAccountOpeartion() {
    let operatype:string=this.operationForm.value.operationType;
    let amount:number=this.operationForm.value.amount
    let description=this.operationForm.value.description
    let accountDestination=this.operationForm.value.accountDestination
    if(operatype=='DEBIT'){
      this.accountService.debit(this.accountId,amount,description).subscribe(
        {
          next:(data)=>{
            alert("Success")
            this.operationForm.reset();
            this.handleSearchAccount();
          },
          error: err => {
            console.log(err);
          }
        }
      )
    }
    else  if(operatype=='CREDIT') {
      this.accountService.credit(this.accountId,amount,description).subscribe(
        {
          next:(data)=>{
            alert("Success")
            this.operationForm.reset();
            this.handleSearchAccount();
          },
          error: err => {
            console.log(err);
          }
        }
      )
    }
    else if (operatype=='TRANSFERT'){
      this.accountService.transfert(this.accountId,accountDestination,amount,description).subscribe(
        {
          next:(data)=>{
            alert("Success ")
            this.operationForm.reset();
            this.handleSearchAccount();
          },
          error: err => {
            console.log(err);
          }
        }
      )
    }

  }
}
