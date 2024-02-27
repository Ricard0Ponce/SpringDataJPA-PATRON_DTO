package com.trinity.demo3.students;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//En esta interface no se agrega ninguna anotacion porque Spring Data JPA sabe que se trata de una 
// interfaz que nos da metodos de JPA al extender. 

// Esta interfaz nos va a permitir usar la JPA Interface, que otorgo metodos relacionados con 
// consultas a la base de datos, permite tener mayor utilidad con la base de datos
//En <a,b> pasamos en "a" el nombre de la clase y en "b" el tipo de dato del Id de esa entidad
public interface StudentRepository extends JpaRepository<Student,Integer> {

    // Creamos un metodo que nos permita obtener la lista de estudiantes con el mismo nombre... 
    // ... Dado que JPA no lo otorga
    //Containing nos permite encontrar aquellos que empiezen por esa letra o por el nombre 
    List<Student> findAllByFirstnameContaining(String firstname);
    
}
