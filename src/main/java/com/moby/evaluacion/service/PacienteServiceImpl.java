package com.moby.evaluacion.service;

import com.moby.evaluacion.exception.RecursoNoEncontradoException;
import com.moby.evaluacion.model.Paciente;
import com.moby.evaluacion.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    
    private final PacienteRepository pacienteRepository;
    
    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @Override
    public Paciente crearPaciente(Paciente paciente) {
        // Guardo el paciente directamente
        return pacienteRepository.save(paciente);
    }
    
    @Override
    public Paciente obtenerPacientePorId(Long id) {
        // Busco el paciente, si no está lanzo error
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No pude encontrar un paciente con el ID " + id));
    }
    
    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        // Devuelvo todos los pacientes que tengo
        return pacienteRepository.findAll();
    }
    
    @Override
    public void eliminarPaciente(Long id) {
        // Primero me fijo si existe el paciente
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No hay ningún paciente con ID " + id + " para eliminar");
        }
        // Si existe, lo borro
        pacienteRepository.deleteById(id);
    }
}