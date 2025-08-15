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
        return pacienteRepository.save(paciente);
    }
    
    @Override
    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Paciente con ID " + id + " no encontrado"));
    }
    
    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll();
    }
    
    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Paciente con ID " + id + " no encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}