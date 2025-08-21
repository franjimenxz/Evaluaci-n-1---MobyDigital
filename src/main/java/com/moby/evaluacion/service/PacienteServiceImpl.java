package com.moby.evaluacion.service;

import com.moby.evaluacion.exception.RecursoNoEncontradoException;
import com.moby.evaluacion.model.Paciente;
import com.moby.evaluacion.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    
    private static final Logger logger = LoggerFactory.getLogger(PacienteServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    
    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @Override
    public Paciente crearPaciente(Paciente paciente) {
        logger.info("Creando paciente: {} {}", paciente.getNombre(), paciente.getApellido());
        Paciente resultado = pacienteRepository.save(paciente);
        logger.info("Paciente creado con ID: {}", resultado.getId());
        return resultado;
    }
    
    @Override
    public Paciente obtenerPacientePorId(Long id) {
        // Busco el paciente, si no está lanzo error
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No pude encontrar un paciente con el ID " + id));
    }
    
    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        logger.info("Obteniendo todos los pacientes");
        List<Paciente> pacientes = pacienteRepository.findAll();
        logger.info("Encontrados {} pacientes", pacientes.size());
        return pacientes;
    }
    
    @Override
    public void eliminarPaciente(Long id) {
        logger.info("Eliminando paciente con ID: {}", id);
        if (!pacienteRepository.existsById(id)) {
            logger.warn("No se puede eliminar - paciente con ID {} no existe", id);
            throw new RecursoNoEncontradoException("No hay ningún paciente con ID " + id + " para eliminar");
        }
        pacienteRepository.deleteById(id);
        logger.info("Paciente con ID {} eliminado exitosamente", id);
    }
}