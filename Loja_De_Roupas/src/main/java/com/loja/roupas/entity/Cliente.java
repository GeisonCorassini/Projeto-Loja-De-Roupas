package com.loja.roupas.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 2, max = 100, message = "O Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
    @CPF(message = "CPF invalido")
    private String cpf;
    
    @NotNull(message = "Idade não pode ser nula")
    @Min(value = 0, message = "Idade deve ser maior ou igual a 0")
    @Max(value = 120, message = "Idade deve ser menor ou igual a 120")
    private Integer idade;
    
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O Telefone deve estar no formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx")
    private String telefone;
    
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Venda> vendas;
}
