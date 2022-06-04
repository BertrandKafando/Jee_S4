import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import {CustomerService} from "./Services/customer.service";
import {ReactiveFormsModule} from "@angular/forms";
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';
import { HomeComponent } from './components/home/home.component';
import {LoginComponent} from "./components/auth/login/login.component";
import {InterceptorService, TokenInterceptorProvider} from "./Services/auth/interceptor.service";
import {HttpClientModule} from "@angular/common/http";
import { AccountShowComponent } from './components/account-show/account-show.component';





@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CustomersComponent,
    AccountsComponent,
    NewCustomerComponent,
    CustomerAccountsComponent,
    HomeComponent,
    LoginComponent,
    AccountShowComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,

  ],
  providers: [
    InterceptorService,
    TokenInterceptorProvider,
    CustomerService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
