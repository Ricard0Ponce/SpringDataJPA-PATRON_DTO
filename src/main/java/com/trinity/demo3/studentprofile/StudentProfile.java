package com.trinity.demo3.studentprofile;

import com.trinity.demo3.students.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;
    private String bio; // Descripcion del alumno

    @OneToOne // Indicamos que tenemos una relacion con otra Tabla de la base de datos
    @JoinColumn(name = "student_id") // Con JoinColumn agregamos a nuestra base de datos la llave foranea con la que
                                     // se relaciona
    private Student student; // NOTA: esta variable de Student debe ser igual al mappedBy que tiene la
                             // implementacio

    public Integer getId() {
        return id;
    }

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
