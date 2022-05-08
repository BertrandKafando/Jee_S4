package ma.enset.apirest.service;

import ma.enset.apirest.dtos.StudentDto;

import java.util.List;

public interface IService {
    public StudentDto save(StudentDto studentDto);
    public void delete(StudentDto studentDto);
    List<StudentDto>students();

    public StudentDto getStudent(Long id);

}
