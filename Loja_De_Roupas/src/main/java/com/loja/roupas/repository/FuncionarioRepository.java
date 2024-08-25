package com.loja.roupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.loja.roupas.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Filtro com JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome LIKE %:nome%")
	List<Funcionario> findByNomeContaining(String nome);
    
 // Filtros 
    List<Funcionario> findByMatricula(String matricula); 
    List<Funcionario> findByIdadeLessThan(int idade);
}
