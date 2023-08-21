package com.example.clienteservice.request;

import com.example.clienteservice.domain.morada.Morada;
import com.example.clienteservice.enums.Plano;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAtualizacaoRequest {
    @NotNull
    private Long id;
    @Length(min = 3)
    @Length(max = 15)
    private String nome;
    @Length(min = 3)
    @Length(max = 80)
    private String apelido;
    @Email
    private String email;

    private String senha;

    @Pattern(regexp = "^\\d{8}\\d{1}$")
    private String NIF;
    private String telemovel;

    private Morada morada;

    private Plano plano;
}
