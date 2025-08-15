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
        // Creo el profesional nuevo
        return profesionalRepository.save(profesional);
    }
    
    @Override
    public Profesional obtenerProfesionalPorId(Long id) {
        // Busco al doctor por su ID
        return profesionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No encuentro ningún profesional con ID " + id));
    }
    
    @Override
    public List<Profesional> obtenerTodosLosProfesionales() {
        // Traigo la lista completa de doctores
        return profesionalRepository.findAll();
    }
    
    @Override
    public List<Profesional> obtenerProfesionalesPorEspecialidad(String especialidad) {
        // Filtro por la especialidad que me piden
        return profesionalRepository.findByEspecialidad(especialidad);
    }
    
    @Override
    public void eliminarProfesional(Long id) {
        // Chequeo que exista antes de borrarlo
        if (!profesionalRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No hay un profesional con ID " + id + " para eliminar");
        }
        // Procedo a eliminarlo
        profesionalRepository.deleteById(id);
    }
}