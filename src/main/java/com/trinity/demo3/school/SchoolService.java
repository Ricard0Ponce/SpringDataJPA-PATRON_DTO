package com.trinity.demo3.school;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
// El servicio lleva la inyeccion del Mapper y del repositorio
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto create(SchoolDto dto) {
        var school = schoolMapper.toSchool(dto); // Aqui se pasa el DTO a escuela 
        schoolRepository.save(school);
        return dto; //Aqui se regresa directamente el dto porque ya esta mapeado
    }
    
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll()
        .stream()
        .map(schoolMapper::toSchoolDto)
        .collect(Collectors.toList())
        ;
    }
}
