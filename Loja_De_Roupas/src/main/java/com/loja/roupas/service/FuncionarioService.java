package com.loja.roupas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loja.roupas.entity.Funcionario;
import com.loja.roupas.repository.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteById(Long id) {
        funcionarioRepository.deleteById(id);
    }
    
    public Funcionario updateFuncionario(Long id, Map<String, Object> camposAtualizacao) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o ID: " + id));

        if (camposAtualizacao.containsKey("nome")) {
            funcionario.setNome((String) camposAtualizacao.get("nome"));
        }
        if (camposAtualizacao.containsKey("idade")) {
            funcionario.setIdade((String) camposAtualizacao.get("idade"));
        }
        if (camposAtualizacao.containsKey("matricula")) {
            funcionario.setMatricula((String) camposAtualizacao.get("matricula"));
        }

        return funcionarioRepository.save(funcionario);
    }
    
    public List<Funcionario> findByNomeContaining(String nome) {
        return funcionarioRepository.findByNomeContaining(nome);
    }

    public List<Funcionario> findByMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula);
    }

    public List<Funcionario> findByIdadeLessThan(int idade) {
        return funcionarioRepository.findByIdadeLessThan(idade);
    }
}
