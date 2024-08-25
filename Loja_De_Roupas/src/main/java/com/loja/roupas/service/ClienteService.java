package com.loja.roupas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loja.roupas.entity.Cliente;
import com.loja.roupas.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    	}

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    	}

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
        
    	}
        public void deleteById(Long id) {
            clienteRepository.deleteById(id);
        }
        
        public Cliente updateCliente(Long id, Map<String, Object> camposAtualizacao) {
        	Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));

            if (camposAtualizacao.containsKey("nome")) {
                cliente.setNome((String) camposAtualizacao.get("nome"));
            }
            if (camposAtualizacao.containsKey("cpf")) {
                cliente.setCpf((String) camposAtualizacao.get("cpf"));
            }
            if (camposAtualizacao.containsKey("idade")) {
                cliente.setIdade((Integer) camposAtualizacao.get("idade"));
            }
            if (camposAtualizacao.containsKey("telefone")) {
                cliente.setTelefone((String) camposAtualizacao.get("telefone"));
            }
            return clienteRepository.save(cliente);
        }
        public List<Cliente> findByNomeContaining(String nome) {
            return clienteRepository.findByNomeContaining(nome);
        }
        public List<Cliente> findByCpf(String cpf) {
            return clienteRepository.findByCpf(cpf);
        }

        public List<Cliente> findByIdadeGreaterThan(int idade) {
            return clienteRepository.findByIdadeGreaterThan(idade);
        }
    }