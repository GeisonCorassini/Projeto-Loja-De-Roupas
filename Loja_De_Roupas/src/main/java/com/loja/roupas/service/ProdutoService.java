package com.loja.roupas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.roupas.entity.Produto;
import com.loja.roupas.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
    
    public Produto updateProduto(Long id, Map<String, Object> camposAtualizacao) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        if (camposAtualizacao.containsKey("nome")) {
            produto.setNome((String) camposAtualizacao.get("nome"));
        }
        if (camposAtualizacao.containsKey("valor")) {
            produto.setValor((BigDecimal) camposAtualizacao.get("valor"));
        }

        return produtoRepository.save(produto);
    }
    
    public List<Produto> findByNomeContaining(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public List<Produto> findByValorGreaterThan(BigDecimal valor) {
        return produtoRepository.findByValorGreaterThan(valor);
    }

}
