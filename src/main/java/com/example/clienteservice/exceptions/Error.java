package com.example.clienteservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
