package com.moby.evaluacion.model;

import java.time.LocalDate;

public class Turno {
    
    private Long id;
    private LocalDate fecha;
    private Paciente paciente;
    private Profesional profesional;
    
    public Turno() {
    }
    
    public Turno(Long id, LocalDate fecha, Paciente paciente, Profesional profesional) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.profesional = profesional;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Profesional getProfesional() {
        return profesional;
    }
    
    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
    
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", paciente=" + paciente +
                ", profesional=" + profesional +
                '}';
    }
}