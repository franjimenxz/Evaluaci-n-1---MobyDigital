package com.moby.evaluacion.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DatoInvalidoException.class)
    public ResponseEntity<String> manejarDatoInvalido(DatoInvalidoException ex) {
        logger.warn("Dato inválido: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionGeneral(Exception ex) {
        logger.error("Error inesperado: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Ha ocurrido un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}