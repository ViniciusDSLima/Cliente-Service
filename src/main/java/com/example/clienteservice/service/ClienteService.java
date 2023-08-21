package com.example.clienteservice.service;

import com.example.clienteservice.DTO.ClienteDTO;
import com.example.clienteservice.domain.Cliente;
import com.example.clienteservice.repository.ClienteRepository;
import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import com.example.clienteservice.request.ClienteCadastroRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO salvarCliente(ClienteCadastroRequest clienteCadastroRequest) {
        var cliente = clienteRepository.save(new Cliente(clienteCadastroRequest));

        return new ClienteDTO(cliente);
    }
    @Transactional
    public ClienteDTO atualizarCliente(ClienteAtualizacaoRequest clienteAtualizacaoRequest) {
        var clienteAtualizado = clienteRepository.getReferenceById(clienteAtualizacaoRequest.getId());
        clienteAtualizado.atualizarInformacoes(clienteAtualizacaoRequest);

        return new ClienteDTO(clienteAtualizado);
    }
}
