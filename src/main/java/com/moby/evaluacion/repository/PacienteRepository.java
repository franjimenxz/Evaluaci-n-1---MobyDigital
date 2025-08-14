package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Paciente;
import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    
    Paciente save(Paciente paciente);
    
    Optional<Paciente> findById(Long id);
    
    List<Paciente> findAll();
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}