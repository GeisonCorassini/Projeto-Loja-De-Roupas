package com.loja.roupas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.loja.roupas.entity.Venda;
import com.loja.roupas.service.VendaService;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody Venda venda) {
        if (venda == null || venda.getCliente() == null || venda.getFuncionario() == null) {
            return ResponseEntity.badRequest().build();
        }
        Venda savedVenda = vendaService.save(venda);
        return ResponseEntity.ok(savedVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @RequestBody Map<String, Object> camposAtualizacao) {
        try {
            Venda vendaAtualizada = vendaService.updateVenda(id, camposAtualizacao);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
