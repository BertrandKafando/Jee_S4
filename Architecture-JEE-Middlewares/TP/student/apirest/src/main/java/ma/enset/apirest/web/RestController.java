package ma.enset.apirest.web;

import lombok.AllArgsConstructor;
import ma.enset.apirest.dtos.StudentDto;

import ma.enset.apirest.service.IService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
   IService studentservice;
    @GetMapping( "/etudiants")
    List<StudentDto> etudiants(){
        List<StudentDto> etudiants=studentservice.students();
        return etudiants;
    }
    @RequestMapping("/delete/{id}")  //au lieu de delete
    public void  delete(@PathVariable("id")  Long ide){
      StudentDto studentDto=studentservice.getStudent(ide);
       studentservice.delete(studentDto);
    }
    @PostMapping("/add")
    public  StudentDto save( @RequestBody StudentDto studentDto){
       StudentDto studentDto1= studentservice.save(studentDto);
        return studentDto1;
    }

}
