package com.trinity.demo3.students;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    // La inyeccion dependencias necesarioas para la logica de negocio va acá
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto); // Hacemos paso de parametros del DTO a toStudent que se encarga de
                                                    // Mappearlo
        // para que sea Estudiante, eso es lo que almacenamos en la BD
        repository.save(student); // Esta variable me permite
        // asignar a esa instancia a saveStudent, considero que es inecesario, se puede
        // pasar directamente el parametro de Student al metodo para mapearlo: var
        // saveStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(student); // Lo que regresa el endpoint es la información básica y no
                                                            // más, esto
        // lo permite el metodo
    }

    public List<StudentResponseDto> findAllStudent() {
        return repository.findAll()
        .stream()
        .map(studentMapper::toStudentResponseDto) // Permite mapear la respuesta sin tener que hacerlo manualmente
        .collect(Collectors.toList()) // Mapea en formato de lista
        ;
    }

    public StudentResponseDto findStudentById(Integer id) {
        return repository.findById(id)
            .map(studentMapper::toStudentResponseDto) //Mapea para que pueda estar representado un objeto a otro en formato DTO
            .orElse(null); // Metodo de JPA que nos permite obtener todos los alumnos
    }

    public List<StudentResponseDto> findStudentByName(String name) {
        return repository.findAllByFirstnameContaining(name)
        .stream()
        .map(studentMapper::toStudentResponseDto)
        .collect(Collectors.toList())
        ; // Metodo creado en StudentsRepository que permite obtener
                                                              // toda la lista de alumnos con ese name
    }

    public void deleteStudentById(Integer Id) {
        repository.deleteById(Id);
    }

}
