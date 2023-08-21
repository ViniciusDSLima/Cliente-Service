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
public class ClienteCadastroRequest {
    @NotBlank
    @NotEmpty
    @Length(min = 3)
    @Length(max = 15)
    private String nome;
    @NotBlank
    @NotEmpty
    @Length(min = 3)
    @Length(max = 80)
    private String apelido;
    @NotNull
    @Email
    @NotEmpty
    private String email;
    @NotBlank
    private String senha;
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\d{8}\\d{1}$")
    private String NIF;
    @NotBlank
    @NotEmpty
    private String telemovel;
    @NotNull
    private Morada morada;
    @NotNull
    private Plano plano;

}
