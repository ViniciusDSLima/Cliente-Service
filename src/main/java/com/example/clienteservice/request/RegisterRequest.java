package com.example.clienteservice.request;

import com.example.clienteservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RegisterRequest(String email, String password, UserRole role) {

}
