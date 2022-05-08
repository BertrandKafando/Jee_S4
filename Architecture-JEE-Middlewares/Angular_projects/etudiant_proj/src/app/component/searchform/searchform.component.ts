import { Component, OnInit } from '@angular/core';
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AppDataState, DataStateEnum} from "../../student.state";
import {Student} from "../../Model/Student.model";
import {StudentService} from "../../service/student.service";

@Component({
  selector: 'app-searchform',
  templateUrl: './searchform.component.html',
  styleUrls: ['./searchform.component.css']
})
export class SearchformComponent implements OnInit {

  students$:Observable<AppDataState<Student[]>>|null=null
  readonly DataStateEnum=DataStateEnum;
  totalElts:any;
  page:number=1;
  constructor(private studentService:StudentService) { }

  ngOnInit(): void {

  }

  onGetAllProducts(data:any){
    this.students$=this.studentService.getAllstudents(data.name)
      .pipe(
        map((data)=>({
          dataState:DataStateEnum.LOADED,  data:data })),
        startWith( {  dataState:DataStateEnum.LOADING}),
        catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
      );
  }

}
