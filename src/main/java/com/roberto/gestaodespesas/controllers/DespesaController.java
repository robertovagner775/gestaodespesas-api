package com.roberto.gestaodespesas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.gestaodespesas.model.Despesa;
import com.roberto.gestaodespesas.model.Saldo;
import com.roberto.gestaodespesas.model.dto.ErrorResponseDto;
import com.roberto.gestaodespesas.model.dto.ResponseDto;
import com.roberto.gestaodespesas.service.DespesaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/despesa")
public class DespesaController {
   
    @Autowired
    public DespesaService despesaService;
    
    @PostMapping
    public ResponseEntity adicionarDespesa(@RequestBody Despesa despesa) {
       return despesaService.cadastrar(despesa);
    }

    @PutMapping
    public ResponseEntity editarDespesa(@RequestBody Despesa despesa, @RequestParam(name = "id") int id) {
        return despesaService.atualizar(despesa, id);
    }

    @GetMapping 
    public ResponseEntity<List<Despesa>> listarDespesa() {
        return ResponseEntity.ok().body(despesaService.listar());
    } 

    @DeleteMapping
    public ResponseEntity<?> deletarDespesa(@RequestBody Despesa despesa) {
       return despesaService.deletar(despesa);
    }

}
