package com.trinity.demo3.school;

//El mapper no lleva ninguna inyeccion de dependencias
public class SchoolMapper {

    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
    
}
