package com.loja.roupas.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;
    
    @NotNull(message = "Idade não pode ser nula")
    @Min(value = 0, message = "Idade deve ser maior ou igual a 0")
    @Max(value = 120, message = "Idade deve ser menor ou igual a 120")
    private String idade;
    
    @NotBlank(message = "Matrícula não pode ser vazia")
    @Pattern(regexp = "[A-Za-z0-9]{8}", message = "Matrícula deve ter exatamente 8 caracteres alfanuméricos")
    private String matricula;
    
    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    private List<Venda> vendas;
}
