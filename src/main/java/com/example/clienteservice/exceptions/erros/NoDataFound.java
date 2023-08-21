package com.example.clienteservice.exceptions.erros;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class NoDataFound extends RuntimeException{
    public NoDataFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFound(String message) {
        super(message);
    }
}
