import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EtudiantComponent} from "./component/etudiant/etudiant.component";
import {FormstudentComponent} from "./component/formstudent/formstudent.component";
import {StudsComponent} from "./component/studs/studs.component";

const routes: Routes = [
  {path:"etu",component:EtudiantComponent},
  {path:"new",component:FormstudentComponent},
  {path:"students",component:StudsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
