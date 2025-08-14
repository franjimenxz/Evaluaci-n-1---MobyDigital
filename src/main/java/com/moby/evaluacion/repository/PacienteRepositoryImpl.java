package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PacienteRepositoryImpl implements PacienteRepository {
    
    private final Map<Long, Paciente> dataSource = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    @Override
    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(idGenerator.getAndIncrement());
        }
        dataSource.put(paciente.getId(), paciente);
        return paciente;
    }
    
    @Override
    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(dataSource.get(id));
    }
    
    @Override
    public List<Paciente> findAll() {
        return new ArrayList<>(dataSource.values());
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