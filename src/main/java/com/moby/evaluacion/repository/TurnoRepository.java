package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Turno;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnoRepository {
    
    Turno save(Turno turno);
    
    Optional<Turno> findById(Long id);
    
    List<Turno> findAll();
    
    List<Turno> findByFecha(LocalDate fecha);
    
    List<Turno> findByPacienteId(Long pacienteId);
    
    List<Turno> findByProfesionalId(Long profesionalId);
    
    boolean existsByPacienteIdAndProfesionalIdAndFecha(Long pacienteId, Long profesionalId, LocalDate fecha);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}