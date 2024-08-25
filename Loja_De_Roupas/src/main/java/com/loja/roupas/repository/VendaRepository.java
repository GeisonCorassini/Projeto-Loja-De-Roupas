package com.loja.roupas.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.loja.roupas.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
	List<Venda> findByClienteId(Long clienteId);
	List<Venda> findByFuncionarioId(Long funcionarioId);
	
    // Filtro com JPQL
    @Query("SELECT v FROM Venda v WHERE v.valorTotal >= :valor")
    List<Venda> findByValorTotalGreaterThanEqual(BigDecimal valor);

    // Filtros 
    List<Venda> findByEnderecoEntrega(String endereco);
    List<Venda> findByValorTotalLessThan(BigDecimal valor);

}
