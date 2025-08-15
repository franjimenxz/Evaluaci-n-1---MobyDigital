package com.moby.evaluacion.controller;

import com.moby.evaluacion.model.Profesional;
import com.moby.evaluacion.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesionales")
public class ProfesionalController {
    
    private final ProfesionalService profesionalService;
    
    @Autowired
    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }
    
    @PostMapping
    public ResponseEntity<Profesional> crearProfesional(@RequestBody Profesional profesional) {
        Profesional profesionalCreado = profesionalService.crearProfesional(profesional);
        return new ResponseEntity<>(profesionalCreado, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Profesional>> obtenerProfesionales(
            @RequestParam(value = "especialidad", required = false) String especialidad) {
        
        List<Profesional> profesionales;
        
        if (especialidad != null && !especialidad.trim().isEmpty()) {
            profesionales = profesionalService.obtenerProfesionalesPorEspecialidad(especialidad);
        } else {
            profesionales = profesionalService.obtenerTodosLosProfesionales();
        }
        
        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }
}