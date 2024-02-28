package com.trinity.demo3.students;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List; // Esta importacion es la buena y no la de Hibernate

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Este endpoint nos permite almacenar a un alumno gracias a un metodo de JPA.
    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid @RequestBody StudentDto dto) {
        return this.studentService.saveStudent(dto); // Llama al metodo studenService
    }

    // Endpoint que permitira traer a todos los alumnos
    // Nota: Como ejercicio mappear esta lista con un DTO para que la informacion
    // que se muestra sea solo la necesaria
    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent() {
        return this.studentService.findAllStudent(); // Metodo de JPA que nos permite obtener todos los alumnos
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findById(
            @PathVariable("student-id") Integer id) {
        // El .orElse es necesario, pues da una respuesta en caso de no encontrar al
        // alumno
        return this.studentService.findStudentById(id);
    }

    // En este endpoint buscamos la lista de alumnos que tengan el nombre que se
    // pasa como parametro
    // Sin embargo, desde la interfaz de StudentRepository creamos ese metodo dado
    // que JPA no lo otorgaba en sus librerias
    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentsByName(
            @PathVariable("student-name") String name) {
        // El .orElse es necesario, pues da una respuesta en caso de no encontrar al
        // alumno
        return this.studentService.findStudentByName(name); // Metodo creado en StudentsRepository que permite obtener
                                                            // toda la lista de alumnos con ese name
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id) {
        this.studentService.deleteStudentById(id);
    }

    /*
     * Este método maneja la excepción MethodArgumentNotValidException, que se lanza
     * cuando falla la validación de un argumento de método anotado con @Valid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        // Se crea un HashMap para almacenar los errores.
        var errors = new HashMap<String, String>();
        // Se obtienen todos los errores de la excepción.
        exp.getBindingResult().getAllErrors().forEach(error -> {
            // Se obtiene el nombre del campo que causó el error.
            var fieldName = ((FieldError) error).getField();
            // Se obtiene el mensaje de error predeterminado.
            var errorMessage = error.getDefaultMessage();
            // Se agrega el error al HashMap.
            errors.put(fieldName, errorMessage);
        });
        // Se devuelve una ResponseEntity con el HashMap de errores y el código de
        // estado BAD_REQUEST.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
