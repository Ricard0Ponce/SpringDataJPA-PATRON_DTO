package com.trinity.demo3;

// Con Record podemos hacer nuestros DTO de manera más sencilla. 
// Recordar que un DTO permite filtrar la información y de esta manera que las entidades solo...
// ... muestren los datos que queremos mostrar
// En este caso, los unicos datos que vamos a pedir para crear un alumno seran los siguientes, es decir quitamos la edad de la formula
public record StudentDto(
        String firstname,
        String lastname,
        String email, 
        Integer schoolId
) {
//Agregamos Integer schoolId porque de esta manera podemos relacionar el StudentDto con la escuela
}
