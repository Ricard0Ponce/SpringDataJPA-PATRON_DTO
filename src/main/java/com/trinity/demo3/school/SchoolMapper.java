package com.trinity.demo3.school;

import org.springframework.stereotype.Service;

//El mapper no lleva ninguna inyeccion de dependencias
@Service
public class SchoolMapper {

    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
    
}
