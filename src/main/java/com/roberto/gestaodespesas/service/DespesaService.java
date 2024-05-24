package com.roberto.gestaodespesas.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.roberto.gestaodespesas.model.Despesa;


@Service
public interface DespesaService {

    ResponseEntity<?> cadastrar(Despesa despesa);

    ResponseEntity<?> atualizar(Despesa despesa, int id);

    ResponseEntity<?> deletar(Despesa despesa);

    ResponseEntity<?> atualizarSaldo(Double valor);

   ResponseEntity<?> definirSaldoInicial(Double valor);

    List<Despesa> listar(); 

    double verificarSaldo(); 

    
} 
