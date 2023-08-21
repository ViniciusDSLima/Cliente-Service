package com.example.clienteservice.controller;

import com.example.clienteservice.DTO.ClienteDTO;
import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import com.example.clienteservice.request.ClienteCadastroRequest;
import com.example.clienteservice.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService service;

    @PostMapping("/cadastrar")
    @Operation(summary = "cadastro do cliente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro do negocio"),
            @ApiResponse(responseCode = "500", description = "erro do servidor")
    })
    public ResponseEntity cadastrarCliente(@RequestBody @Valid ClienteCadastroRequest clienteCadastroRequest){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(service.salvarCliente(clienteCadastroRequest));
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualiza o cadastro do cliente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro do negocio"),
            @ApiResponse(responseCode = "500", description = "erro do servidor")
    })
    public ResponseEntity atualizarCliente(@RequestBody @Valid ClienteAtualizacaoRequest clienteAtualizacaoRequest){
        return ResponseEntity.status(HttpStatus.OK.value()).body(service.atualizarCliente(clienteAtualizacaoRequest));
    }
}
