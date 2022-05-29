import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthentificationService} from "../../../Services/auth/authentification.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authForm!:FormGroup
  accessToken!:any
  constructor(private fb:FormBuilder,private authService:AuthentificationService,private router:Router ) { }

  ngOnInit(): void {
    this.authForm=this.fb.group(
      {
        username:this.fb.control("Bertrand"),
        password:this.fb.control("1234")
      }
    )
  }

  handleAuthentication() {
    let username:String=this.authForm.value.username;
    let password:String=this.authForm.value.password;
    console.log(username+""+password);

    this.authService.login(username,password).subscribe(
      {
        next:data=>{
          alert("authentification been successfully!")
          // this.newCustomerform.reset()
          this.accessToken=data;
          console.log(this.accessToken)
          this.router.navigateByUrl("/home")
        },
        error: err => {
          console.log(err);
        }
      }
    )
  }
}
