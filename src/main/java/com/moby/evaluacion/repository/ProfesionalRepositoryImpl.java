package com.moby.evaluacion.repository;

import com.moby.evaluacion.model.Profesional;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProfesionalRepositoryImpl implements ProfesionalRepository {
    
    private final Map<Long, Profesional> dataSource = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    @Override
    public Profesional save(Profesional profesional) {
        if (profesional.getId() == null) {
            profesional.setId(idGenerator.getAndIncrement());
        }
        dataSource.put(profesional.getId(), profesional);
        return profesional;
    }
    
    @Override
    public Optional<Profesional> findById(Long id) {
        return Optional.ofNullable(dataSource.get(id));
    }
    
    @Override
    public List<Profesional> findAll() {
        return new ArrayList<>(dataSource.values());
    }
    
    @Override
    public List<Profesional> findByEspecialidad(String especialidad) {
        return dataSource.values().stream()
                .filter(profesional -> especialidad.equals(profesional.getEspecialidad()))
                .collect(Collectors.toList());
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