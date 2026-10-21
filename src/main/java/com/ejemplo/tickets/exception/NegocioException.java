package com.ejemplo.tickets.exception;
public class NegocioException extends RuntimeException {
    public NegocioException(String mensaje) { super(mensaje); }
}
