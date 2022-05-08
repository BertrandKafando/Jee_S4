import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";
import {StudentService} from "../../service/student.service";

@Component({
  selector: 'app-formstudent',
  templateUrl: './formstudent.component.html',
  styleUrls: ['./formstudent.component.css']
})
export class FormstudentComponent implements OnInit {

    studentformGroup!:FormGroup;
    submitted:boolean=false;

  constructor(private fb:FormBuilder,private studentService:StudentService) { }

  ngOnInit(): void {
    this.studentformGroup=this.fb.group(
      {
        name:["",Validators.required],
        surname:[""],
        dateNaissance:[""],
        regle:[""]
      }
    );
  }

  onSaveStudent(){
    this.submitted=true;
    if(this.studentformGroup?.invalid)return
  this.studentService.save(this.studentformGroup?.value)
    .subscribe(
      data=>{
        alert("sucess saving product")
      }
    )
  }

}
