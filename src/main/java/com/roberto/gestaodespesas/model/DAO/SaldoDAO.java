package com.roberto.gestaodespesas.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.roberto.gestaodespesas.database.DbConnection;

@Repository
public class SaldoDAO {
    
    
    public Boolean atualizarSaldo(Double valor) { 
        double saldoAtual = verificarSaldo();
        try{
            String sql = "UPDATE SALDO SET VALOR = ?  WHERE ID = 1";
        
            PreparedStatement pt = null;
    
            Connection conn = DbConnection.getConexao();
            pt = conn.prepareStatement(sql);
            pt.setDouble(1, valor + saldoAtual);
            
            if(pt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
    public Boolean decrementarSaldo(Double valor) { 
        double saldoAtual = verificarSaldo();
        try{
            String sql = "UPDATE SALDO SET VALOR = ?  WHERE ID = 1";
        
            PreparedStatement pt = null;
    
            Connection conn = DbConnection.getConexao();
            pt = conn.prepareStatement(sql);
            pt.setDouble(1, saldoAtual - valor);
            
            if(pt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double verificarSaldo() {
        String query = "SELECT valor FROM saldo WHERE id = 1";
        Connection conn = DbConnection.getConexao();
        try ( PreparedStatement  stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
           double value = 0;
            while (rs.next()) {
                value = value + rs.getDouble("valor");
            }
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.00;
    }
    }

    
    public Boolean definirSaldoInicial(Double valor) {
        try{
            int count = verificaSaldoExistente();
            if(count == 0) {
                    System.out.println("numero de linhas: "+ count);
                    String sql = "INSERT INTO Saldo (id, valor) VALUES (1, ?) ";     

                    PreparedStatement pt = null;
                
                    Connection conn = DbConnection.getConexao();
                    pt = conn.prepareStatement(sql);

                    pt.setDouble(1, valor);
                
                
                    if(pt.executeUpdate() > 0) {
                        return true;
                    } else {
                        return false;
                    }
            } else {
  
                return atualizarSaldo(valor);
            }
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }

    public int verificaSaldoExistente() {
        try{
            Connection connection = DbConnection.getConexao();
            String checkSql = "SELECT COUNT(*) FROM Saldo WHERE id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, 1);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 2; 
        }
    }


}
