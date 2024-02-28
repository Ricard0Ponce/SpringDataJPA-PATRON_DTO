package com.trinity.demo3.students;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentMapperTest {

    private StudentMapper mapper; // Se declara la variable con la que trabajaremos
    // El BeforeEach inicializara la variable declarada antes de que se ejecute el
    // metodo declarado

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "Jonh",
                "Doe",
                "John@mail.com",
                1);
        Student student = mapper.toStudent(dto); // Sabemos que este metodo nos dara un Objeto Student si esta correcto
        /* Estos primeros 3 nos aseguran que los valores sean iguales */
        Assertions.assertEquals(dto.firstname(), student.getFirstname());
        Assertions.assertEquals(dto.lastname(), student.getLastname());
        Assertions.assertEquals(dto.email(), student.getEmail());
        /* El siguiente nos asegura que sea no nulo */
        assertNotNull(student.getSchool());
        /* El siguiente compara el id de la esucela */
        Assertions.assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test // Test para detectar null
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        var msg = assertThrows(NullPointerException.class, () -> mapper.toStudent(null)); // Obtengo el mensaje de error
        assertEquals("El DTO del estudiante no deberia ser nulo", msg.getMessage()); // Comparo el mensaje de error
    }

    @Test
    public void shouldMapStudentToResponseStudentDTO() {
        // Givne
        Student student = new Student("Jade",
                "Smith",
                "jade@mail.com",
                25);
        // When
        StudentResponseDto studMap = mapper.toStudentResponseDto(student); // Aqui es cuando pasamos de Student a
                                                                           // StudentResponseDTO
        //
        Assertions.assertEquals(student.getFirstname(), studMap.firstname());
        Assertions.assertEquals(student.getLastname(), studMap.lastname());
        Assertions.assertEquals(student.getEmail(), studMap.email());
    }

    /*
     * //BeforeAll se ejecuta al inicio de toda la prueba y solo lo hace una vez, es
     * decir, inicializa 1 vez aunque haya 2 metodos
     * // Por lo que solo se ejecuta una vez
     * 
     * @BeforeAll
     * static void BeforeAll(){
     * System.out.println("Inside the before all method");
     * }
     * // Nos sirve para eliminar los datos que se inyectaron a una base de datos
     * para la prueba
     * // Nuevamente, este metodo solo se ejecuta una vez hasta el final
     * 
     * @AfterAll
     * static void AfterAll(){
     * System.out.println("Inside the after all method");
     * }
     * 
     * 
     * // Este metodo ejecuta antes de que cada test se ejecute, por lo que aquí se
     * // inicializan algunas variables
     * 
     * @BeforeEach //
     * void setUp() {
     * System.out.println("Inside the before each method");
     * }
     * 
     * //Este metodo se ejecuta hasta el final del Testing
     * 
     * @AfterEach
     * void tearDown() {
     * System.out.println("Inside the after each method"); // Este mensaje se
     * muestra hasta el final
     * }
     * 
     * // Cuando creamos un test debe ser public void siempre
     * 
     * @Test // Se agrega la etiqueta al metodo
     * public void testMetod1() {
     * System.out.println("My first test method");
     * }
     * 
     * // Cuando creamos un test debe ser public void siempre
     * 
     * @Test // Se agrega la etiqueta al metodo
     * public void testMetod2() {
     * System.out.println("My second test method");
     * }
     */
}
