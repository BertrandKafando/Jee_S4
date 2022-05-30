import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthentificationService} from "../../../Services/auth/authentification.service";
import * as moment from "moment";
import {shareReplay, tap} from "rxjs";
import {Token} from "../../../Model/login";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authForm!:FormGroup
  submitting=false;
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
    this.submitting=true
    let username:string=this.authForm.value.username;
    let password:string=this.authForm.value.password;
    console.log(username+""+password);

    this.authService.loginRequest(username,password) .then(bolean=>{
      if( bolean){
        this.router.navigate(["/home"]);
      }
    }).catch(any=>{
     // this.toastr.warning( '', 'incorrect username or password !', { closeButton: true, positionClass: "toast-top-center" });
    }).finally(()=>{
      this.submitting=false
    })

  }

/*
  getToken() {
    return localStorage.getItem('access_token');
  }
  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return authToken !== null ? true : false;
  }
  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    if (removeToken == null) {
      this.router.navigate(['login']);
    }
  }
*/









}
