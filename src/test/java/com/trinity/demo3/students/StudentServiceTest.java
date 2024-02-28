package com.trinity.demo3.students;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

    // Que servicio vamos a testear
    @InjectMocks // Permite realizar la inyeccion de sus dependencias para poder hacer la prueba
    private StudentService studentService; // Este objeto tiene dependencias, para repository y mapper.
    // Declaramos las dependencias anteriormente mencionadas
    @Mock // Le agregamos esta etiqueta a las dependencias que tenga inyectadas nuestros
          // metodos
    StudentRepository repository;
    @Mock
    StudentMapper studentMapper;
    // Ahora le decimos a Mockito que inicialize los Mocks...
    // ... Para ello usamos el BeforeEach

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Permite abrir los mocks
    }

    @Test
    public void should_succesfully_save_students() {
        // Given
        // El student que viene como parametro
        StudentDto dto = new StudentDto(
                "Jonh",
                "Doe",
                "John@mail.com",
                1);
        // El mappeo a estudiante
        Student student = new Student(
                "Jonh",
                "Doe",
                "John@mail.com",
                20);
        // El almacenamiento del estudiante con el ID asignado
        Student savedStudent = new Student(
                "Jonh",
                "Doe",
                "John@mail.com",
                20);
        savedStudent.setId(1);

        // Ahora debemos de Mockear las llamadas
        /*
         * Forma 1 de escribirlo:
         * Mockito.when(studentMapper.toStudent(dto)).thenReturn(student); // El mock va
         * a funcionar solo si paso el objeto
         */
        Mockito.when(studentMapper.toStudent(dto))
                .thenReturn(student); // El mock va a funcionar solo si paso el objeto
        Mockito.when(repository.save(student))
                .thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "Jonh",
                        "Doe",
                        "John@mail.com"));

        // When "Agregamos la funcionalidad que estamos estudiando"
        StudentResponseDto responseDto = studentService.saveStudent(dto); // Se realiza el almacenamiento del objeto
        // Then "Que esperamos obtener"
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        Mockito.verify(studentMapper, times(1)).toStudent(dto); // Verifica que el método toStudent del objeto
                                                                // studentMapper haya sido llamado exactamente una vez
                                                                // con el argumento dto.
        Mockito.verify(repository, times(1)).save(student); // Verifica que el método save del objeto repository haya
                                                            // sido llamado exactamente una vez con el argumento
                                                            // student.
        Mockito.verify(studentMapper, times(1)).toStudentResponseDto(savedStudent); // Verifica que el método
                                                                                    // toStudentResponseDto del objeto
                                                                                    // studentMapper haya sido llamado
                                                                                    // exactamente una vez con el
                                                                                    // argumento savedStudent.

    }

    // Este método es un test unitario que verifica que el método findAllStudent de
    // la clase StudentService
    // devuelve correctamente todos los estudiantes.
    @Test
    public void should_return_all_students() {
        // Given
        // Creamos una lista de estudiantes con un solo estudiante.
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Jonh",
                "Doe",
                "John@mail.com",
                20));

        // Mockeamos las llamadas al repositorio y al mapper.
        // Configuramos que cuando se llame al método findAll del repositorio, devuelva
        // la lista de estudiantes creada.
        Mockito.when(repository.findAll()).thenReturn(students);
        // Configuramos que cuando se llame al método toStudentResponseDto del
        // studentMapper, devuelva un objeto StudentResponseDto con los datos del
        // estudiante creado.
        Mockito.when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Jonh",
                        "Doe",
                        "John@mail.com"));

        // When
        // Llamamos al método findAllStudent de studentService para obtener la lista de
        // estudiantes.
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        // Then
        // Verificamos que el tamaño de la lista de estudiantes devuelta sea igual al
        // tamaño de la lista students.
        assertEquals(students.size(), responseDtos.size());
        // Verificamos que el método findAll del repositorio se haya llamado exactamente
        // una vez.
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        // Given
        // Creamos al alumno:
        Integer studentId = 1;
        Student student = new Student(
                "Jonh",
                "Doe",
                "John@mail.com",
                20);
        // student.setId(1); //Asignamos el valor que llevara
        // Mockeamos el metodo
        Mockito.when(repository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Jonh",
                        "Doe",
                        "John@mail.com"));

        // When
        StudentResponseDto dto = studentService.findStudentById(studentId);
        // Then
        // Then "Que esperamos obtener"
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        Mockito.verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_students_by_name(){
        String studentname = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Jonh",
                "Doe",
                "John@mail.com",
                20));
        //Mockeamos el metodo
        Mockito.when(repository.findAllByFirstnameContaining(studentname)).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(any(Student.class)))
        .thenReturn(new StudentResponseDto(
                "Jonh",
                "Doe",
                "John@mail.com"));
        //When
        List<StudentResponseDto> responseDtos = studentService.findStudentByName(studentname);
        //Then
        assertEquals(students.size(), responseDtos.size());
        Mockito.verify(repository, times(1)).findAllByFirstnameContaining(studentname);


    }

}
