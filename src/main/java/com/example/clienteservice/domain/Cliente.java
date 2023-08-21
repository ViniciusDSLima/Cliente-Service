package com.example.clienteservice.domain;

import com.example.clienteservice.domain.morada.Morada;
import com.example.clienteservice.enums.Plano;
import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import com.example.clienteservice.request.ClienteCadastroRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "NIF")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String apelido;
    private String email;
    private String senha;
    private String NIF;
    private String telemovel;
    @Embedded
    private Morada morada;
    @Enumerated(EnumType.STRING)
    private Plano plano;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime cadastro;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime atualizacao;
    public Cliente(ClienteCadastroRequest clienteCadastroRequest) {
        this.nome = clienteCadastroRequest.getNome();
        this.apelido = clienteCadastroRequest.getApelido();
        this.email = clienteCadastroRequest.getEmail();
        this.senha = clienteCadastroRequest.getSenha();
        this.NIF = clienteCadastroRequest.getNIF();
        this.telemovel = clienteCadastroRequest.getTelemovel();
        this.morada = clienteCadastroRequest.getMorada();
        this.plano = clienteCadastroRequest.getPlano();
        this.cadastro = LocalDateTime.now();
    }

    public void atualizarInformacoes(ClienteAtualizacaoRequest clienteAtualizacaoRequest) {
        if(clienteAtualizacaoRequest.getNome() != null) this.nome = clienteAtualizacaoRequest.getNome();
        if(clienteAtualizacaoRequest.getEmail() != null) this.email = clienteAtualizacaoRequest.getEmail();
        if(clienteAtualizacaoRequest.getSenha() != null) this.senha = clienteAtualizacaoRequest.getSenha();
        if(clienteAtualizacaoRequest.getNIF() != null) this.NIF= clienteAtualizacaoRequest.getNIF();
        if(clienteAtualizacaoRequest.getTelemovel() != null) this.telemovel = clienteAtualizacaoRequest.getTelemovel();
        if(clienteAtualizacaoRequest.getPlano() != null) this.plano = clienteAtualizacaoRequest.getPlano();
        if(clienteAtualizacaoRequest.getMorada() != null) morada.atualizarInformacoes(clienteAtualizacaoRequest);
        this.atualizacao = LocalDateTime.now();
    }
}
