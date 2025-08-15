package com.moby.evaluacion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());
        respuesta.put("error", "Recurso no encontrado");
        respuesta.put("message", ex.getMessage());
        respuesta.put("path", "");
        
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DatoInvalidoException.class)
    public ResponseEntity<Map<String, Object>> manejarDatoInvalido(DatoInvalidoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "Datos inválidos");
        respuesta.put("message", ex.getMessage());
        respuesta.put("path", "");
        
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcionGeneral(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("error", "Error interno del servidor");
        respuesta.put("message", "Ha ocurrido un error inesperado");
        respuesta.put("path", "");
        
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}