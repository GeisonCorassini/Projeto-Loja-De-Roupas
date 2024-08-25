package com.loja.roupas.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.loja.roupas.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Filtro com JPQL
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
	List<Produto> findByNomeContaining(String nome);
    
    List<Produto> findByValorGreaterThan(BigDecimal valor);//Filtro 
}
