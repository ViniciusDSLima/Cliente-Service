package com.example.clienteservice.service;

import com.example.clienteservice.DTO.ClienteDTO;
import com.example.clienteservice.domain.Cliente;
import com.example.clienteservice.exceptions.erros.IdNotFoundException;
import com.example.clienteservice.exceptions.erros.NoDataFound;
import com.example.clienteservice.exceptions.erros.ObjectNotFoundException;
import com.example.clienteservice.repository.ClienteRepository;
import com.example.clienteservice.request.ClienteAtualizacaoRequest;
import com.example.clienteservice.request.ClienteCadastroRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
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
    public ClienteDTO atualizarCliente(Long id, ClienteAtualizacaoRequest clienteAtualizacaoRequest) {

        if(id == 0){
            throw new IdNotFoundException("Id nao pode ser nulo");
        }
        if(!clienteRepository.findById(id).isPresent()){
            throw new IdNotFoundException("Id nao cadastrado no banco de dados");
        }
        var clienteAtualizado = clienteRepository.getReferenceById(id);
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

    public void deleteById(Long id) {

        var cliente = clienteRepository.findById(id);

        if(!cliente.isPresent()){
            throw new ObjectNotFoundException("Cliente nao encontrado na base de dados");
        }

        clienteRepository.deleteById(id);
    }
}
