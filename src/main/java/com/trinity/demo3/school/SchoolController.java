package com.trinity.demo3.school;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SchoolController {
    private final SchoolService schoolService; // El controlador lleva la inyeccion del servicio

    // Inyeccion de dependencias por medio del constructor
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    // Endpoint que nos permite almacenar una escuela
    // El requestBody indica que ese objeto debe pasarse
    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto) {
        return schoolService.create(dto);
    }
    // Endpoint que regresa la lista de escuelas que tenemos almacenadas
    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolService.findAll();
    }
}
