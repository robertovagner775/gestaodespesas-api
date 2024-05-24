package com.roberto.gestaodespesas.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import com.roberto.gestaodespesas.database.DbConnection;
import com.roberto.gestaodespesas.model.Categoria;
import com.roberto.gestaodespesas.model.Despesa;
import com.roberto.gestaodespesas.model.DAO.DespesaDAO;
import com.roberto.gestaodespesas.model.DAO.SaldoDAO;
import com.roberto.gestaodespesas.model.dto.ErrorResponseDto;
import com.roberto.gestaodespesas.model.dto.ResponseDto;

@Service
public class DespesaServiceImp implements DespesaService {

    @Autowired
    private DespesaDAO despesaDAO;

    @Autowired
    private SaldoDAO saldoDAO;

    @Override
    public ResponseEntity<?> cadastrar(Despesa despesa)  {
        if(saldoDAO.verificaSaldoExistente() == 0) {
            return ResponseEntity.status(200).body(new ErrorResponseDto("e necessario definir um saldo inicial !!!", "erro ao adicionar despesa", 200));
        }

        double saldoDisponivel = saldoDAO.verificarSaldo();
        if (despesa.getValor() < saldoDisponivel) {   
            Boolean test = saldoDAO.decrementarSaldo(despesa.getValor());
            if(despesaDAO.adicionarDespesa(despesa) && test) {
              return ResponseEntity.status(201).body(new ResponseDto("nova despesa adicionada", 201));
            }
            return ResponseEntity.status(0).body(new ErrorResponseDto("saldo insuficiente", "erro ao adicionar despesa", 200));
        } 
        return ResponseEntity.status(0).body(new ErrorResponseDto("saldo insuficiente", "erro ao adicionar despesa", 200));
          
    }

    @Override
    public ResponseEntity<?> atualizar(Despesa despesa, int id) {
        double saldoAtual = saldoDAO.verificarSaldo();  
        double valorDiferencaDespesa = despesaDAO.buscaDespesaValor(id)  - despesa.getValor();
        
        if(saldoDAO.verificaSaldoExistente() == 0) {
            return ResponseEntity.status(200).body(new ErrorResponseDto("e necessario definir um saldo inicial !!!", "erro ao atualizar saldo", 200));
        }
        if (despesa.getValor() > saldoAtual) {
            return ResponseEntity.ok().body(new ErrorResponseDto("saldo insuficiente","erro ao atualizar despesa", 200));
        }
        if(saldoDAO.atualizarSaldo(valorDiferencaDespesa) && despesaDAO.atualizarDespesa(despesa, id)) {
            return ResponseEntity.ok().body(new ResponseDto("despesa atualizada", 200));
        }
        return ResponseEntity.status(200).body(new ErrorResponseDto("erro ao atualizar despesa", "erro ao atualizar despesa", 200));

    }

    @Override
    public List<Despesa> listar( ) {
        return despesaDAO.listarDespesa();
    }

   

    @Override
    public ResponseEntity<?> atualizarSaldo(Double valor) { 
            
        if(saldoDAO.verificaSaldoExistente() == 0) {
            return ResponseEntity.status(200).body(new ErrorResponseDto("e necessario definir um saldo inicial !!!", "erro ao atualizar saldo", 200));
        }

        if( saldoDAO.atualizarSaldo(valor)) {
            return ResponseEntity.ok().body(new ResponseDto("Saldo Atualizado", 200));
        }
        return ResponseEntity.ok().body(new ErrorResponseDto("saldo insuficiente", "Não foi Possivel Atualizar Saldo", 200));

    }

    @Override
    public double verificarSaldo() {
       
      return  saldoDAO.verificarSaldo();

        
    }

    @Override
    public ResponseEntity<?> definirSaldoInicial(Double valor) {
        if(saldoDAO.definirSaldoInicial(valor)) {
            return ResponseEntity.status(201).body(new ResponseDto("Saldo inicial definido", 201));
        }
        return ResponseEntity.ok().body(new ErrorResponseDto("erro ao definir saldo", "Não foi Possivel definir saldo", 200));

    }

    @Override
    public ResponseEntity<?> deletar(Despesa despesa) {
        if(saldoDAO.verificaSaldoExistente() == 0) {
            return ResponseEntity.status(200).body(new ErrorResponseDto("e necessario definir um saldo inicial !!!", "erro ao deletar despesa", 200));
        }
        if (saldoDAO.atualizarSaldo(despesa.getValor()) && despesaDAO.deletar(despesa)) {
            return ResponseEntity.status(200).body(new ResponseDto("Despesa Excluida Com Sucesso", 200));
        } 
        return ResponseEntity.ok().body(new ErrorResponseDto("despesa não encontrada", "não foi possivel deletar a despesa", 200));
        
    }

   
    }
    

