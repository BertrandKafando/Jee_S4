package ma.enset.apirest.mappers;

import ma.enset.apirest.dtos.StudentDto;
import ma.enset.apirest.entities.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student fromstudentDto(StudentDto studentDto){
        Student student=new Student();
        BeanUtils.copyProperties(studentDto,student);
        return  student;
    }

    public StudentDto fromstudent(Student student){
        StudentDto studentDto=new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return  studentDto;
    }

}
