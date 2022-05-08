import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { EtudiantComponent } from './component/etudiant/etudiant.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StudsComponent} from "./component/studs/studs.component";
import {StudentService} from "./service/student.service";
import {NgxPaginationModule} from "ngx-pagination";
import { SearchformComponent } from './component/searchform/searchform.component';
import {FormstudentComponent} from "./component/formstudent/formstudent.component";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    EtudiantComponent,
       StudsComponent,
       SearchformComponent,
    FormstudentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [StudentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
