import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Student} from "../Model/Student.model";

@Injectable()
export class StudentService{
constructor(private http:HttpClient) {}
getAllstudents(name:String):Observable<Student[]>{
  let host=environment.host;
  return this.http.get<Student[]>(host+"/etudiants")
}

deletestudent(p:Student):Observable<void>{
  let host=environment.host;
  return this.http.delete<void>(host+"/delete/"+p.id);
}
save(student:Student):Observable<Student>{
  let host=environment.host;
  return this.http.post<Student>(host+"/add",student);
}
}
