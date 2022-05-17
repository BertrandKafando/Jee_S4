import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";
import {Customer} from "../Model/customers.model";
import {CustomerService} from "../../Services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {
  newCustomerform!:FormGroup;
  constructor(private fb:FormBuilder,private customerService:CustomerService,private router:Router ) { }

  ngOnInit(): void {
    this.newCustomerform=this.fb.group(
      {
        name:["",Validators.required],
        email:this.fb.control(null,[Validators.email])
      }
    );
  }

  handleformnewcustomer() {
    let customer:Customer=this.newCustomerform.value;
  this.customerService.saveCustomer(customer).subscribe(
    {
      next:data=>{
        alert("Customer has been successfully saved!")
       // this.newCustomerform.reset()
        this.router.navigateByUrl("/customers")
      },
      error: err => {
        console.log(err);
      }
    }
  );

  }
}
