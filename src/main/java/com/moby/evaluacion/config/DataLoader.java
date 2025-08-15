package com.moby.evaluacion.config;

import com.moby.evaluacion.model.Paciente;
import com.moby.evaluacion.model.Profesional;
import com.moby.evaluacion.model.Turno;
import com.moby.evaluacion.service.PacienteService;
import com.moby.evaluacion.service.ProfesionalService;
import com.moby.evaluacion.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final PacienteService pacienteService;
    private final ProfesionalService profesionalService;
    private final TurnoService turnoService;
    
    @Autowired
    public DataLoader(PacienteService pacienteService, 
                     ProfesionalService profesionalService, 
                     TurnoService turnoService) {
        this.pacienteService = pacienteService;
        this.profesionalService = profesionalService;
        this.turnoService = turnoService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Cargando datos iniciales...");
        
        // Crear pacientes
        Paciente franco = new Paciente();
        franco.setNombre("Franco");
        franco.setApellido("Jiménez");
        franco.setDni("35123456");
        franco.setEmail("franco.jimenez@email.com");
        franco = pacienteService.crearPaciente(franco);
        
        Paciente santiago = new Paciente();
        santiago.setNombre("Santiago");
        santiago.setApellido("López");
        santiago.setDni("28987654");
        santiago.setEmail("santiago.lopez@email.com");
        santiago = pacienteService.crearPaciente(santiago);
        
        Paciente valentina = new Paciente();
        valentina.setNombre("Valentina");
        valentina.setApellido("Rodríguez");
        valentina.setDni("32456789");
        valentina.setEmail("valentina.rodriguez@email.com");
        valentina = pacienteService.crearPaciente(valentina);
        
        Paciente matias = new Paciente();
        matias.setNombre("Matías");
        matias.setApellido("González");
        matias.setDni("29654321");
        matias.setEmail("matias.gonzalez@email.com");
        matias = pacienteService.crearPaciente(matias);
        
        // Crear profesionales
        Profesional drFernandez = new Profesional();
        drFernandez.setNombreCompleto("Dr. Carlos Fernández");
        drFernandez.setEspecialidad("Cardiología");
        drFernandez = profesionalService.crearProfesional(drFernandez);
        
        Profesional draMartinez = new Profesional();
        draMartinez.setNombreCompleto("Dra. Ana Martínez");
        draMartinez.setEspecialidad("Dermatología");
        draMartinez = profesionalService.crearProfesional(draMartinez);
        
        Profesional draMoreno = new Profesional();
        draMoreno.setNombreCompleto("Dra. Lucía Moreno");
        draMoreno.setEspecialidad("Pediatría");
        draMoreno = profesionalService.crearProfesional(draMoreno);
        
        Profesional drSilva = new Profesional();
        drSilva.setNombreCompleto("Dr. Diego Silva");
        drSilva.setEspecialidad("Neurología");
        drSilva = profesionalService.crearProfesional(drSilva);
        
        // Crear turnos
        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.now().plusDays(1));
        turno1.setPaciente(franco);
        turno1.setProfesional(drFernandez);
        turnoService.crearTurno(turno1);
        
        Turno turno2 = new Turno();
        turno2.setFecha(LocalDate.now().plusDays(2));
        turno2.setPaciente(santiago);
        turno2.setProfesional(draMartinez);
        turnoService.crearTurno(turno2);
        
        Turno turno3 = new Turno();
        turno3.setFecha(LocalDate.now().plusDays(3));
        turno3.setPaciente(valentina);
        turno3.setProfesional(draMoreno);
        turnoService.crearTurno(turno3);
        
        Turno turno4 = new Turno();
        turno4.setFecha(LocalDate.now().plusDays(5));
        turno4.setPaciente(matias);
        turno4.setProfesional(drSilva);
        turnoService.crearTurno(turno4);
        
        Turno turno5 = new Turno();
        turno5.setFecha(LocalDate.now().plusDays(7));
        turno5.setPaciente(franco);
        turno5.setProfesional(draMoreno);
        turnoService.crearTurno(turno5);
        
        System.out.println("Datos iniciales cargados exitosamente:");
        System.out.println("- 4 Pacientes creados");
        System.out.println("- 4 Profesionales creados");
        System.out.println("- 5 Turnos programados");
    }
}