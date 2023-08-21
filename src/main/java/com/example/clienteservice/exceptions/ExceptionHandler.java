package com.example.clienteservice.exceptions;

import com.example.clienteservice.exceptions.erros.IdEmpty;
import com.example.clienteservice.exceptions.erros.NoDataFound;
import com.example.clienteservice.exceptions.erros.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Error> objectNotFoundException(ObjectNotFoundException ex,
                                                         HttpServletRequest request){

        Error error = new Error(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object not found",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoDataFound.class)
    public ResponseEntity<Error> noDataFound(NoDataFound ex, HttpServletRequest request){

        Error error = new Error(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Base de dados vazia",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(IdEmpty.class)
    public ResponseEntity<Error> noIdPresent(NoDataFound ex, HttpServletRequest request){

        Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Bad Request",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
