package com.example.clienteservice.exceptions.erros;

public class IdEmpty extends RuntimeException{
    public IdEmpty(String message) {
        super(message);
    }

    public IdEmpty(String message, Throwable cause) {
        super(message, cause);
    }
}
