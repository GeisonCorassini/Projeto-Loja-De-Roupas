package com.loja.roupas.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @DecimalMin(value = "0.01", message = "Valor deve ser maior ou igual a 0.01")
    @DecimalMax(value = "10000.00", message = "Valor deve ser menor ou igual a 10.000.00")
    @NotNull(message = "Preço não pode ser nulo")
    @Min(value = 0, message = "Preço deve ser maior ou igual a 0")
    private BigDecimal valor;
    
    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private List<Venda> vendas;
}
