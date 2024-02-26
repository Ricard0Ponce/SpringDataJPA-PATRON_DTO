package com.trinity.demo3;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class SchoolController {
    
    // Declaramos el SchoolRepository
    private SchoolRepository schoolRepository;
    //Inyeccion de dependencias por medio del constructor
    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    // Endpoint que nos permite almacenar una escuela
    // El requestBody indica que ese objeto debe pasarse
    @PostMapping("/schools")
    public SchoolDto create(
        @RequestBody SchoolDto dto
        ) {
        var school =toSchool(dto); // Aqui se pasa el DTO a escuela 
        schoolRepository.save(school);
        return dto; //Aqui se regresa directamente el dto porque ya esta mapeado
        }

    

    private School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    private SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
    // Endpoint que regresa la lista de escuelas que tenemos almacenadas
    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll()
        .stream()
        .map(this::toSchoolDto)
        .collect(Collectors.toList());
    }
}
