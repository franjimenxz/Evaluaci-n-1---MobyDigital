package com.moby.evaluacion.service;

import com.moby.evaluacion.model.Turno;
import java.time.LocalDate;
import java.util.List;

public interface TurnoService {
    
    Turno crearTurno(Turno turno);
    
    Turno obtenerTurnoPorId(Long id);
    
    List<Turno> obtenerTodosLosTurnos();
    
    List<Turno> obtenerTurnosPorFecha(LocalDate fecha);
    
    List<Turno> obtenerTurnosPorRangoFechas(LocalDate desde, LocalDate hasta);
    
    List<Turno> obtenerTurnosPorPaciente(Long pacienteId);
    
    List<Turno> obtenerTurnosPorProfesional(Long profesionalId);
    
    void eliminarTurno(Long id);
}