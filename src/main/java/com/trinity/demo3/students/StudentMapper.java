package com.trinity.demo3.students;

import org.springframework.stereotype.Service;

import com.trinity.demo3.school.School;
@Service
public class StudentMapper {
//Los metodos deben ir publicos aquí para que el controlador tenga acceso a ellos. 

    // Creamos un metodo para que el DTO pueda ser mapeado al objeto Estudiante que
    // queremos almacenar
    // En este DTO no se usa la edad de usuario como ejemplo
    public Student toStudent(StudentDto dto) {
        if(dto == null){
            throw new NullPointerException("El DTO del estudiante no deberia ser nulo");
        }

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
    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }
}
