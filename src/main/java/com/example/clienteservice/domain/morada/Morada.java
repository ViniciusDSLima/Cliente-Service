package com.example.clienteservice.domain.morada;

import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Morada {

    private String morada;
    private String localidade;
    private String codigo;

    public void atualizarInformacoes(ClienteAtualizacaoRequest clienteAtualizacaoRequest) {
        if(clienteAtualizacaoRequest.getMorada().getMorada() != null) this.morada = clienteAtualizacaoRequest.getMorada().getMorada();
        if(clienteAtualizacaoRequest.getMorada().getLocalidade() != null) this.localidade = clienteAtualizacaoRequest.getMorada().getLocalidade();
        if(clienteAtualizacaoRequest.getMorada().getCodigo() != null) this.codigo = clienteAtualizacaoRequest.getMorada().getCodigo();
    }
}
