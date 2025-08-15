package com.moby.evaluacion.service;

import com.moby.evaluacion.model.Profesional;
import java.util.List;

public interface ProfesionalService {
    
    Profesional crearProfesional(Profesional profesional);
    
    Profesional obtenerProfesionalPorId(Long id);
    
    List<Profesional> obtenerTodosLosProfesionales();
    
    List<Profesional> obtenerProfesionalesPorEspecialidad(String especialidad);
    
    void eliminarProfesional(Long id);
}