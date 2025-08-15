package com.moby.evaluacion.service;

import com.moby.evaluacion.exception.RecursoNoEncontradoException;
import com.moby.evaluacion.model.Profesional;
import com.moby.evaluacion.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    
    private final ProfesionalRepository profesionalRepository;
    
    @Autowired
    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }
    
    @Override
    public Profesional crearProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }
    
    @Override
    public Profesional obtenerProfesionalPorId(Long id) {
        return profesionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Profesional con ID " + id + " no encontrado"));
    }
    
    @Override
    public List<Profesional> obtenerTodosLosProfesionales() {
        return profesionalRepository.findAll();
    }
    
    @Override
    public List<Profesional> obtenerProfesionalesPorEspecialidad(String especialidad) {
        return profesionalRepository.findByEspecialidad(especialidad);
    }
    
    @Override
    public void eliminarProfesional(Long id) {
        if (!profesionalRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Profesional con ID " + id + " no encontrado");
        }
        profesionalRepository.deleteById(id);
    }
}