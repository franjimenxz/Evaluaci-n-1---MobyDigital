package com.moby.evaluacion.exception;

public class DatoInvalidoException extends RuntimeException {
    
    public DatoInvalidoException(String message) {
        super(message);
    }
    
    public DatoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}