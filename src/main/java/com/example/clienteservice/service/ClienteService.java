package com.example.clienteservice.service;

import com.example.clienteservice.DTO.ClienteDTO;
import com.example.clienteservice.domain.Cliente;
import com.example.clienteservice.exceptions.erros.IdEmpty;
import com.example.clienteservice.exceptions.erros.NoDataFound;
import com.example.clienteservice.exceptions.erros.ObjectNotFoundException;
import com.example.clienteservice.repository.ClienteRepository;
import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import com.example.clienteservice.request.ClienteCadastroRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(clienteAtualizacaoRequest.getId() == null){
            throw new IdEmpty("Id nulo no corpo da requisicao");
        }
        var clienteAtualizado = clienteRepository.getReferenceById(clienteAtualizacaoRequest.getId());
        clienteAtualizado.atualizarInformacoes(clienteAtualizacaoRequest);

        return new ClienteDTO(clienteAtualizado);
    }


    public List<Cliente> findAll() {
        if (clienteRepository.findAll().isEmpty()){
            throw new NoDataFound("Nehum cliente cadastrado na base de dados");
        }
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Cliente nao encontrado na base de dados"));
    }
}
