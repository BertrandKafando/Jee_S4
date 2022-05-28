import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../../Services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../../Model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
    formGroupAccount!:FormGroup
  operationForm!:FormGroup
  currentPage:number=0;
    pagesize:number=5;
    accountObservable!:Observable<AccountDetails>
  errorMessage!:String

  constructor(private fb:FormBuilder,private accountService:AccountsService) { }

  ngOnInit(): void {
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
      let accountId:String=this.formGroupAccount.value.accountId
   this.accountObservable= this.accountService.getAccount(accountId,this.currentPage,this.pagesize).pipe(
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
  let accounId:string=this.formGroupAccount.value.accountId;
  let operatype:string=this.operationForm.value.operationType;
  let amount:number=this.operationForm.value.amount
    let description=this.operationForm.value.description
  let accountDestination=this.operationForm.value.accountDestination
  if(operatype=='DEBIT'){
    this.accountService.debit(accounId,amount,description).subscribe(
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
  this.accountService.credit(accounId,amount,description).subscribe(
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
    this.accountService.transfert(accounId,accountDestination,amount,description).subscribe(
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
