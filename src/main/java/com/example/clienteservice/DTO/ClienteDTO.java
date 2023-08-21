package com.example.clienteservice.DTO;

import com.example.clienteservice.domain.Cliente;
import com.example.clienteservice.domain.morada.Morada;
import com.example.clienteservice.enums.Plano;
import com.example.clienteservice.request.ClienteCadastroRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nome;
    private String apelido;
    private String email;
    private String NIF;
    private String telemovel;
    private Morada morada;
    private Plano plano;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime cadastro;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizacao;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.apelido = cliente.getApelido();
        this.email = cliente.getEmail();
        this.NIF = cliente.getNIF();
        this.telemovel = cliente.getTelemovel();
        this.morada = cliente.getMorada();
        this.plano = cliente.getPlano();
        this.cadastro = cliente.getCadastro();
        this.atualizacao = cliente.getAtualizacao();
    }
}
