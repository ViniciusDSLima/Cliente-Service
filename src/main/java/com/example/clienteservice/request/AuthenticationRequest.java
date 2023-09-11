package com.example.clienteservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AuthenticationRequest(String email, String password) {
}
