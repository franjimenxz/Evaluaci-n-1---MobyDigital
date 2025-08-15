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
        // Primero verifico que el paciente existe
        Paciente paciente = pacienteRepository.findById(turno.getPaciente().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No pude encontrar al paciente con ID " + turno.getPaciente().getId()));
        
        // Ahora chequeo que el profesional también existe
        Profesional profesional = profesionalRepository.findById(turno.getProfesional().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No hay ningún doctor con ID " + turno.getProfesional().getId()));
        
        // Me fijo si ya hay un turno igual (mismo paciente, doctor y fecha)
        boolean yaHayTurno = turnoRepository.existsByPacienteIdAndProfesionalIdAndFecha(
                paciente.getId(), 
                profesional.getId(), 
                turno.getFecha()
        );
        
        if (yaHayTurno) {
            throw new DatoInvalidoException("Ya hay un turno programado para " + paciente.getNombre() + 
                    " " + paciente.getApellido() + " con " + profesional.getNombreCompleto() + 
                    " el día " + turno.getFecha());
        }
        
        // Todo bien, guardo el turno
        turno.setPaciente(paciente);
        turno.setProfesional(profesional);
        
        return turnoRepository.save(turno);
    }
    
    @Override
    public Turno obtenerTurnoPorId(Long id) {
        // Busco el turno específico
        return turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No hay ningún turno con ID " + id));
    }
    
    @Override
    public List<Turno> obtenerTodosLosTurnos() {
        // Devuelvo todos los turnos que tengo guardados
        return turnoRepository.findAll();
    }
    
    @Override
    public List<Turno> obtenerTurnosPorFecha(LocalDate fecha) {
        // Filtro los turnos por la fecha que me piden
        return turnoRepository.findByFecha(fecha);
    }
    
    @Override
    public List<Turno> obtenerTurnosPorRangoFechas(LocalDate desde, LocalDate hasta) {
        // Busco turnos entre las dos fechas que me dan
        return turnoRepository.findByFechaEntre(desde, hasta);
    }
    
    @Override
    public List<Turno> obtenerTurnosPorPaciente(Long pacienteId) {
        // Primero verifico que el paciente existe
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new RecursoNoEncontradoException("No existe un paciente con ID " + pacienteId);
        }
        // Si existe, busco sus turnos
        return turnoRepository.findByPacienteId(pacienteId);
    }
    
    @Override
    public List<Turno> obtenerTurnosPorProfesional(Long profesionalId) {
        // Me fijo que el profesional exista
        if (!profesionalRepository.existsById(profesionalId)) {
            throw new RecursoNoEncontradoException("No encuentro un profesional con ID " + profesionalId);
        }
        // Busco todos sus turnos
        return turnoRepository.findByProfesionalId(profesionalId);
    }
    
    @Override
    public void eliminarTurno(Long id) {
        // Chequeo que el turno existe antes de borrarlo
        if (!turnoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No hay un turno con ID " + id + " para eliminar");
        }
        // Lo elimino
        turnoRepository.deleteById(id);
    }
}