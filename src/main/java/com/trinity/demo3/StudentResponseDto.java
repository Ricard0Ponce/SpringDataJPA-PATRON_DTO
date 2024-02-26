package com.trinity.demo3;
// Esta es la informacion que le exponemos al cliente despues de crear el objeto
public record StudentResponseDto(
    String firstname,
    String lastname, 
    String email
) {    
}
