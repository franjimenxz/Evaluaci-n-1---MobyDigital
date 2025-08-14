package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Profesional;
import java.util.List;
import java.util.Optional;

public interface ProfesionalRepository {
    
    Profesional save(Profesional profesional);
    
    Optional<Profesional> findById(Long id);
    
    List<Profesional> findAll();
    
    List<Profesional> findByEspecialidad(String especialidad);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}