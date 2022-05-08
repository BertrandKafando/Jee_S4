import { Component, OnInit } from '@angular/core';
import {StudentService} from "../../service/student.service";
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AppDataState, DataStateEnum} from "../../student.state";
import {Student} from "../../Model/Student.model";

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.css']
})
export class EtudiantComponent implements OnInit {

  students$:Observable<AppDataState<Student[]>>|null=null
  readonly DataStateEnum=DataStateEnum;
  totalElts:any;
  page:number=1;
  constructor(private studentService:StudentService) { }

  ngOnInit(): void {
    this.onGetAllProducts();

  }

  onGetAllProducts(){
    this.students$=this.studentService.getAllstudents("")
      .pipe(
        map((data)=>({
          dataState:DataStateEnum.LOADED,  data:data })),
        startWith( {  dataState:DataStateEnum.LOADING}),
        catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
      );
  }

  onDelete(p:Student){
    console.log(p);
this.studentService.deletestudent(p).subscribe(
  data=>{
    this.onGetAllProducts();
  }
)
  }

}
