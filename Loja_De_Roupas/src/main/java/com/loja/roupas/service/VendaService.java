package com.loja.roupas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loja.roupas.entity.Cliente;
import com.loja.roupas.entity.Funcionario;
import com.loja.roupas.entity.Produto;
import com.loja.roupas.entity.Venda;
import com.loja.roupas.repository.ClienteRepository;
import com.loja.roupas.repository.FuncionarioRepository;
import com.loja.roupas.repository.ProdutoRepository;
import com.loja.roupas.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }

    public Venda save(Venda venda) {
        Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                                           .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Funcionario funcionario = funcionarioRepository.findById(venda.getFuncionario().getId())
                                                       .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        List<Produto> produtos = venda.getProdutos().stream()
                                      .map(produto -> produtoRepository.findById(produto.getId())
                                                                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produto.getId())))
                                      .collect(Collectors.toList());

        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setProdutos(produtos);

        BigDecimal valorTotal = produtos.stream()
                .map(Produto::getValor) 
                .reduce(BigDecimal.ZERO, BigDecimal::add); 
        venda.setValorTotal(valorTotal);	

        return vendaRepository.save(venda);
    }

    public void deleteById(Long id) {
        vendaRepository.deleteById(id);
    }
    
    public Venda updateVenda(Long id, Map<String, Object> camposAtualizacao) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada com o ID: " + id));

        if (camposAtualizacao.containsKey("enderecoEntrega")) {
            venda.setEnderecoEntrega((String) camposAtualizacao.get("enderecoentrega"));
        }
        if (camposAtualizacao.containsKey("valorTotal")) {
            venda.setValorTotal((BigDecimal) camposAtualizacao.get("valorTotal"));
        }
        if (camposAtualizacao.containsKey("cliente")) {
            Cliente cliente = clienteRepository.findById((Long) camposAtualizacao.get("cliente"))
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
            venda.setCliente(cliente);
        }
        if (camposAtualizacao.containsKey("funcionario")) {
            Funcionario funcionario = funcionarioRepository.findById((Long) camposAtualizacao.get("funcionario"))
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
            venda.setFuncionario(funcionario);
        }
        if (camposAtualizacao.containsKey("produtos")) {	
            List<Long> idsProdutos = (List<Long>) camposAtualizacao.get("produtos");
            List<Produto> produtos = produtoRepository.findAllById(idsProdutos);
            venda.setProdutos(produtos);
        }

        return vendaRepository.save(venda);
    }
    
    public List<Venda> findByClienteId(Long clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }

    public List<Venda> findByFuncionarioId(Long funcionarioId) {
        return vendaRepository.findByFuncionarioId(funcionarioId);
    }
    public List<Venda> findByValorTotalGreaterThanEqual(BigDecimal valor) {
        return vendaRepository.findByValorTotalGreaterThanEqual(valor);
    }

    public List<Venda> findByEnderecoEntrega(String endereco) {
        return vendaRepository.findByEnderecoEntrega(endereco);
    }

    public List<Venda> findByValorTotalLessThan(BigDecimal valor) {
        return vendaRepository.findByValorTotalLessThan(valor);
    }
}
