package com.trinity.demo3;

import org.springframework.web.bind.annotation.RestController;
import java.util.List; // Esta importacion es la buena y no la de Hibernate

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Este endpoint nos permite almacenar a un alumno gracias a un metodo de JPA.
    @PostMapping("/students")
    public StudentResponseDto post(
            @RequestBody StudentDto dto) {
        var student = toStudent(dto); // Hacemos paso de parametros del DTO a toStudent que se encarga de Mappearlo
                                      // para que sea Estudiante, eso es lo que almacenamos en la BD
        // var saveStudent = repository.save(student); // Esta variable me permite
        // asignar a esa instancia a saveStudent, considero que es inecesario, se puede
        // pasar directamente el parametro de Student al metodo para mapearlo
        return toStudentResponseDto(student); // Lo que regresa el endpoint es la información básica y no más, esto
                                              // lo permite el metodo
    }

    // Creamos un metodo para que el DTO pueda ser mapeado al objeto Estudiante que
    // queremos almacenar
    // En este DTO no se usa la edad de usuario como ejemplo
    private Student toStudent(StudentDto dto) {
        var student = new Student(); // Declaramos una variable de tipo estuidiante
        // Asignamos los valores que contiene el DTO a sus correspondientes campos
        student.setFirstname(dto.firstname()); // Se asigna directamente porque el dto pertenece a un record y este
                                               // tiene las variables directas
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        var school = new School(); // Asignamos una al objeto Escuela
        school.setId(dto.schoolId()); // Le asignamos el id que tiene el DTO al objeto Escuela
        student.setSchool(school); // al objeto Estudiante le asignamos la escuela.
        return student; // Finalmente regresamos un objeto de tipo Estudmaiye
    }

    // Creamos un método que nos mapea la respuesta del dto, es decir que de la
    // instancia creada
    // Construimos el objeto del DTO y de esta manera la respuesra que regresemos es
    // un objeto de tipo DTO.
    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }

    // Endpoint que permitira traer a todos los alumnos
    // Nota: Como ejercicio mappear esta lista con un DTO para que la informacion
    // que se muestra sea solo la necesaria
    @GetMapping("/students")
    public List<Student> findAllStudent() {
        return repository.findAll(); // Metodo de JPA que nos permite obtener todos los alumnos
    }

    @GetMapping("/students/{student-id}")
    public Student findById(
            @PathVariable("student-id") Integer id) {
        // El .orElse es necesario, pues da una respuesta en caso de no encontrar al
        // alumno
        return repository.findById(id)
                .orElse(null); // Metodo de JPA que nos permite obtener todos los alumnos
    }

    // En este endpoint buscamos la lista de alumnos que tengan el nombre que se
    // pasa como parametro
    // Sin embargo, desde la interfaz de StudentRepository creamos ese metodo dado
    // que JPA no lo otorgaba en sus librerias
    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentsByName(
            @PathVariable("student-name") String name) {
        // El .orElse es necesario, pues da una respuesta en caso de no encontrar al
        // alumno
        return repository.findAllByFirstnameContaining(name); // Metodo creado en StudentsRepository que permite obtener
                                                              // toda la lista de alumnos con ese name
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id) {
        repository.deleteById(id);
    }

}
