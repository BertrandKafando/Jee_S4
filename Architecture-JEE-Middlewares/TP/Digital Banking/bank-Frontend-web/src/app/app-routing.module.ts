import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomersComponent} from "./components/customers/customers.component";
import {AccountsComponent} from "./components/accounts/accounts.component";
import {NewCustomerComponent} from "./components/new-customer/new-customer.component";
import {CustomerAccountsComponent} from "./components/customer-accounts/customer-accounts.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/auth/login/login.component";
import {AccountShowComponent} from "./components/account-show/account-show.component";



const routes: Routes = [
  {path:"customers",component:CustomersComponent},
  {path:"accounts",component:AccountsComponent},
  {path:"accounts-show/:id",component:AccountShowComponent},
  {path:"new-customer",component:NewCustomerComponent},
  {path:"home",component:HomeComponent},
  {path:"login",component:LoginComponent},
  {path:"customer-accounts/:id",component:CustomerAccountsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
