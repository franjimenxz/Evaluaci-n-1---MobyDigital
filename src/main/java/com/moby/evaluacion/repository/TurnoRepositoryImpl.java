package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Turno;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TurnoRepositoryImpl implements TurnoRepository {
    
    private final Map<Long, Turno> dataSource = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    @Override
    public Turno save(Turno turno) {
        if (turno.getId() == null) {
            turno.setId(idGenerator.getAndIncrement());
        }
        dataSource.put(turno.getId(), turno);
        return turno;
    }
    
    @Override
    public Optional<Turno> findById(Long id) {
        return Optional.ofNullable(dataSource.get(id));
    }
    
    @Override
    public List<Turno> findAll() {
        return new ArrayList<>(dataSource.values());
    }
    
    @Override
    public List<Turno> findByFecha(LocalDate fecha) {
        return dataSource.values().stream()
                .filter(turno -> fecha.equals(turno.getFecha()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Turno> findByFechaEntre(LocalDate desde, LocalDate hasta) {
        return dataSource.values().stream()
                .filter(turno -> (turno.getFecha().isEqual(desde) || turno.getFecha().isAfter(desde)) && 
                                (turno.getFecha().isEqual(hasta) || turno.getFecha().isBefore(hasta)))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Turno> findByPacienteId(Long pacienteId) {
        return dataSource.values().stream()
                .filter(turno -> turno.getPaciente() != null && pacienteId.equals(turno.getPaciente().getId()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Turno> findByProfesionalId(Long profesionalId) {
        return dataSource.values().stream()
                .filter(turno -> turno.getProfesional() != null && profesionalId.equals(turno.getProfesional().getId()))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByPacienteIdAndProfesionalIdAndFecha(Long pacienteId, Long profesionalId, LocalDate fecha) {
        return dataSource.values().stream()
                .anyMatch(turno -> 
                    turno.getPaciente() != null && pacienteId.equals(turno.getPaciente().getId()) &&
                    turno.getProfesional() != null && profesionalId.equals(turno.getProfesional().getId()) &&
                    fecha.equals(turno.getFecha())
                );
    }
    
    @Override
    public void deleteById(Long id) {
        dataSource.remove(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return dataSource.containsKey(id);
    }
}