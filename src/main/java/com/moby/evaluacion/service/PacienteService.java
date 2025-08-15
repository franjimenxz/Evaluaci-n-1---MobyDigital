package com.moby.evaluacion.service;

import com.moby.evaluacion.model.Paciente;
import java.util.List;

public interface PacienteService {
    
    Paciente crearPaciente(Paciente paciente);
    
    Paciente obtenerPacientePorId(Long id);
    
    List<Paciente> obtenerTodosLosPacientes();
    
    void eliminarPaciente(Long id);
}