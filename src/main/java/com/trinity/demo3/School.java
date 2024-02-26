package com.trinity.demo3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    @OneToMany(mappedBy = "school") // Indicamos que habra una relacion de uno a muchos: UNA escuela tiene MUCHOS
                                    // estudiantes
    @JsonManagedReference // Esto va a permitir que no tengamos el bucle al hacer ek get de estos elementos
    private List<Student> students; // Indicamos una lista de estudiantes

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public School(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School() {
    }

}
