package ma.enset.apirest.service;

import lombok.AllArgsConstructor;
import ma.enset.apirest.dtos.StudentDto;
import ma.enset.apirest.entities.Student;
import ma.enset.apirest.mappers.StudentMapper;
import ma.enset.apirest.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@AllArgsConstructor
public class IServiceImpl implements IService {
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student=studentMapper.fromstudentDto(studentDto);
      Student savedStudent=  studentRepository.save(student);

        return studentMapper.fromstudent(savedStudent);
    }

    @Override
    public void delete(StudentDto studentDto) {
        Student student=studentMapper.fromstudentDto(studentDto);
       studentRepository.delete(student);
    }

    @Override
    public List<StudentDto> students() {
        List<Student>students=studentRepository.findAll();
        List<StudentDto>studentDtos=
                students.stream().map(
                        p->studentMapper.fromstudent(p)

                ).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public StudentDto getStudent(Long id) {

        Student student=  studentRepository.findById(id).orElse(null);

        return studentMapper.fromstudent(student);
    }
}
