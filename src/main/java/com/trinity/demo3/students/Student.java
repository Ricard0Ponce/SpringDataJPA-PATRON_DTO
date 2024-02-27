package com.trinity.demo3.students;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.trinity.demo3.school.School;
import com.trinity.demo3.studentprofile.StudentProfile;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // Indicamos que es una tabla para la base de datos
@Table(name = "T_STUDENT") // Indicamos el nombre de la tabla en name = " "
public class Student {
    /////////////////////////// SECCION DE VARIABLES ////////////////
    // Campos de nuestra clase
    @Id // Indicamos que este es el identificador de mi tabla
    @GeneratedValue // Pemrite asignar automaticamente el valor del ID cuando se almacena una
                    // entidad sin necesidad de
    // que se tenga que agregar manualmente, además se repite.
    // E
    private Integer id;
    // Con @Column podemos realizar diveros cambios en nuestra columna que se
    // relaciona con ese campo
    @Column(name = "c_fname", length = 20)
    private String firstname;

    private String lastname;
    // unique Inidica que el campo es único, Esto significa que no puede haber dos
    // registros en la tabla que tengan el mismo valor en ese campo.
    @Column(unique = true)
    private String email;

    private int age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL) // Así le declaramos a JPA que se trata de una relación
                                                               // uno a uno
                                                               // mappedBy hace la refrerencia a el campo con el que se
                                                               // relaciona (Llave foranea (?))
                                                               // cascadeType.ALL Si se elimina el estudiante entonces
                                                               // elimina su perfil al mismo tiempo
    private StudentProfile studentProfile;

    @ManyToOne() // Indicamos que MUCHOS estudiantes(Esta clase) pertenecen a UNA ESCUELA
    @JoinColumn(name = "school_id") // Indicamos que vamos a agregar un campo para la llave foranea en nuestra
                                    // entidad.
    @JsonBackReference // Le dice que esta entidad no necesita ser serializado
    private School school;

    /////////////////////////////// COMIENZAN CONSTRUCTORES GETTERS Y SETTERS
    /////////////////////////////// /////////////////////

    // updatable indica que una vez se almacena un objeto, el campo con esta
    // etiqueta ya no se puede actualizar, por ejemplo fecha de creacion
    // insertable hace que el sistema añada ese dato con la fecha de ese momento y
    // no puede ser ni asigado o modificado: ,
    // insertable = false
    /*
     * @Column(updatable = false)
     * private String some_column;
     */

    // Constructor
    public Student(String firstname, String lastname, String email, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    // Agregamos un constructor vacio para JPA
    public Student() {
    }

    // GETERS Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
