package com.moby.evaluacion.controller;

import com.moby.evaluacion.model.Turno;
import com.moby.evaluacion.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoController {
    
    private final TurnoService turnoService;
    
    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        Turno turnoCreado = turnoService.crearTurno(turno);
        return new ResponseEntity<>(turnoCreado, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurnoPorId(@PathVariable Long id) {
        Turno turno = turnoService.obtenerTurnoPorId(id);
        return new ResponseEntity<>(turno, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTodosLosTurnos(
            @RequestParam(value = "desde", required = false) String desde,
            @RequestParam(value = "hasta", required = false) String hasta) {
        
        List<Turno> turnos;
        
        // Si me dan rango de fechas, filtro por ese rango
        if (desde != null && hasta != null) {
            LocalDate fechaDesde = LocalDate.parse(desde);
            LocalDate fechaHasta = LocalDate.parse(hasta);
            turnos = turnoService.obtenerTurnosPorRangoFechas(fechaDesde, fechaHasta);
        } else {
            // Si no, traigo todos
            turnos = turnoService.obtenerTodosLosTurnos();
        }
        
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }
    
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocalDate = LocalDate.parse(fecha);
        List<Turno> turnos = turnoService.obtenerTurnosPorFecha(fechaLocalDate);
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorPaciente(@PathVariable Long pacienteId) {
        List<Turno> turnos = turnoService.obtenerTurnosPorPaciente(pacienteId);
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }
    
    @GetMapping("/profesional/{profesionalId}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorProfesional(@PathVariable Long profesionalId) {
        List<Turno> turnos = turnoService.obtenerTurnosPorProfesional(profesionalId);
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}