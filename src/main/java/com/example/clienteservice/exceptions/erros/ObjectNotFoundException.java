package com.example.clienteservice.exceptions.erros;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
