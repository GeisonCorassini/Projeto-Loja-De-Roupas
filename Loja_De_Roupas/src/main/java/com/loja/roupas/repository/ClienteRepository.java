package com.loja.roupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.loja.roupas.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
    // Filtro com JPQL
    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	List<Cliente> findByNomeContaining(String nome);
    
 // Filtros 
    List<Cliente> findByCpf(String cpf);  
    List<Cliente> findByIdadeGreaterThan(int idade);  
}
