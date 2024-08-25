package com.loja.roupas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String enderecoEntrega;
    private BigDecimal valorTotal;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cliente cliente;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Funcionario funcionario;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "venda_produto",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
        )
    
    private List<Produto> produtos;
}
