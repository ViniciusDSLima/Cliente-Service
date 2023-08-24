package com.example.clienteservice.controller;

import com.example.clienteservice.DTO.LoginResponseDTO;
import com.example.clienteservice.domain.User;
import com.example.clienteservice.repository.UserRepository;
import com.example.clienteservice.request.AuthenticationRequest;
import com.example.clienteservice.request.RegisterRequest;
import com.example.clienteservice.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private TokenService tokenService;

    @PostMapping("/register")
    @Operation(summary = "registra o cliente para poder acessar as diversas funcionalidades de compra dos pacotes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha do negocio")
    })
    public ResponseEntity register(@RequestBody @Valid RegisterRequest request){
        if(this.userRepository.findByEmail(request.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(request.email(), encryptedPassword, request.role());

        this.userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PostMapping("/login")
    @Operation(summary = "autoriza o usuario a comprar os cursos da plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "usuario autorizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha do negocio")
    })
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
