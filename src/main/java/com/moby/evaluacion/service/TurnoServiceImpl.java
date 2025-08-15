package com.moby.evaluacion.service;

import com.moby.evaluacion.exception.DatoInvalidoException;
import com.moby.evaluacion.exception.RecursoNoEncontradoException;
import com.moby.evaluacion.model.Paciente;
import com.moby.evaluacion.model.Profesional;
import com.moby.evaluacion.model.Turno;
import com.moby.evaluacion.repository.PacienteRepository;
import com.moby.evaluacion.repository.ProfesionalRepository;
import com.moby.evaluacion.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {
    
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;
    
    @Autowired
    public TurnoServiceImpl(TurnoRepository turnoRepository, 
                           PacienteRepository pacienteRepository,
                           ProfesionalRepository profesionalRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
    }
    
    @Override
    public Turno crearTurno(Turno turno) {
        // Validar que el paciente existe
        Paciente paciente = pacienteRepository.findById(turno.getPaciente().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el paciente con ID " + turno.getPaciente().getId()));
        
        // Validar que el profesional existe
        Profesional profesional = profesionalRepository.findById(turno.getProfesional().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el profesional con ID " + turno.getProfesional().getId()));
        
        // Validar que no haya turnos duplicados (mismo paciente, profesional y fecha)
        boolean turnoExiste = turnoRepository.existsByPacienteIdAndProfesionalIdAndFecha(
                paciente.getId(), 
                profesional.getId(), 
                turno.getFecha()
        );
        
        if (turnoExiste) {
            throw new DatoInvalidoException("El turno ya existe para el paciente " + paciente.getNombre() + 
                    " " + paciente.getApellido() + " con el profesional " + profesional.getNombreCompleto() + 
                    " en la fecha " + turno.getFecha());
        }
        
        // Asignar las entidades completas al turno
        turno.setPaciente(paciente);
        turno.setProfesional(profesional);
        
        return turnoRepository.save(turno);
    }
    
    @Override
    public Turno obtenerTurnoPorId(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Turno con ID " + id + " no encontrado"));
    }
    
    @Override
    public List<Turno> obtenerTodosLosTurnos() {
        return turnoRepository.findAll();
    }
    
    @Override
    public List<Turno> obtenerTurnosPorFecha(LocalDate fecha) {
        return turnoRepository.findByFecha(fecha);
    }
    
    @Override
    public List<Turno> obtenerTurnosPorPaciente(Long pacienteId) {
        // Verificar que el paciente existe
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new RecursoNoEncontradoException("Paciente con ID " + pacienteId + " no encontrado");
        }
        return turnoRepository.findByPacienteId(pacienteId);
    }
    
    @Override
    public List<Turno> obtenerTurnosPorProfesional(Long profesionalId) {
        // Verificar que el profesional existe
        if (!profesionalRepository.existsById(profesionalId)) {
            throw new RecursoNoEncontradoException("Profesional con ID " + profesionalId + " no encontrado");
        }
        return turnoRepository.findByProfesionalId(profesionalId);
    }
    
    @Override
    public void eliminarTurno(Long id) {
        if (!turnoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Turno con ID " + id + " no encontrado");
        }
        turnoRepository.deleteById(id);
    }
}