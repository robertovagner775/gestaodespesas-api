package com.roberto.gestaodespesas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.gestaodespesas.model.Saldo;
import com.roberto.gestaodespesas.model.dto.ErrorResponseDto;
import com.roberto.gestaodespesas.model.dto.ResponseDto;
import com.roberto.gestaodespesas.service.DespesaService;



@RestController
@RequestMapping("/saldo")
public class SaldoController {


    @Autowired
    public DespesaService despesaService;

    @PutMapping
    public ResponseEntity<?> atualizarSaldo(@RequestBody Saldo saldo) {
        return despesaService.atualizarSaldo(saldo.getValor());
    }

    
    @PostMapping
    public ResponseEntity<?> saldoInicial(@RequestBody Saldo saldo) {
        return despesaService.definirSaldoInicial(saldo.getValor());
    }

     @GetMapping
    public  ResponseEntity<?> getSaldo() {
        return ResponseEntity.ok().body(despesaService.verificarSaldo());
    }
}
