package com.roberto.gestaodespesas.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.roberto.gestaodespesas.database.DbConnection;
import com.roberto.gestaodespesas.model.Categoria;
import com.roberto.gestaodespesas.model.Despesa;

@Repository
public class DespesaDAO {
    
    private DbConnection connection;

  
    public Boolean adicionarDespesa(Despesa despesa)  {
  
                try{
                        String sql = "INSERT INTO DESPESA ( DESCRICAO, VALOR, CATEGORIA, DATA) VALUES ( ?, ?, ?, ?)";
                      

                        PreparedStatement pt = null;
                 
                
                        Connection conn = DbConnection.getConexao();
                        pt = conn.prepareStatement(sql);
                      

                        
        

                        pt.setString(1, despesa.getDescricao());
                        pt.setDouble(2, despesa.getValor());
                        pt.setString(3, despesa.getCategoria().name());
                        pt.setDate(4,   Date.valueOf(despesa.getData().toString()));

                        if(pt.executeUpdate() > 0){
                            return true;
                        } else {
                            return false;
                        }
                   
                
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double buscaDespesaValor(int id)  {
        String sqlBuscarDespesa = "SELECT valor FROM Despesa WHERE id = ?";
        Connection conn = DbConnection.getConexao();
        try(PreparedStatement stmtDespesaBanco = conn.prepareStatement(sqlBuscarDespesa); ){
            stmtDespesaBanco.setInt(1, id);
            ResultSet rs = stmtDespesaBanco.executeQuery();
            if (rs.next())  {
                double valorBanco = rs.getDouble("valor");
                return valorBanco;
            }
            return 0;
        }catch(SQLException e) {
            return 0; 
        }
        
    }

    public List<Despesa> listarDespesa() {
   
        List<Despesa> list = new ArrayList<>();
        String sql = "SELECT * FROM despesa";
   
        try{
            PreparedStatement pt = null;
            Connection conn = DbConnection.getConexao();
            pt = conn.prepareStatement(sql);
            try(ResultSet resultSet = pt.executeQuery()) {
                while(resultSet.next()) {
                    list.add(Despesa.builder()
                    .id(resultSet.getInt("id"))
                    .descricao(resultSet.getString("descricao"))
                    .valor(resultSet.getDouble("valor"))
                    .categoria(Categoria.valueOf(resultSet.getString("categoria")))
                    .data(LocalDate.parse(resultSet.getString("data")))

                    .build()
                    );
                }
            }
         
        }catch (SQLException e) {
            e.printStackTrace();
           
        }

        return list;
    }


    
   
    public Boolean atualizarDespesa(Despesa despesa, int id) {
       
 
          
            String sqlDespesa = "UPDATE DESPESA SET DESCRICAO = ? ,  VALOR = ? , CATEGORIA = ?, DATA = ? WHERE ID = ?";
            
        
    
            Connection conn = DbConnection.getConexao();
            try( PreparedStatement stmtUpdate = conn.prepareStatement(sqlDespesa);) {
                
            
    
                stmtUpdate.setString(1, despesa.getDescricao());
                stmtUpdate.setDouble(2, despesa.getValor());
                stmtUpdate.setString(3, despesa.getCategoria().name());
                stmtUpdate.setDate(4,Date.valueOf(despesa.getData().toString()));
                stmtUpdate.setInt(5, id);


               if(stmtUpdate.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }
        
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }


    
    public Boolean deletar(Despesa despesa) {
    
            try{
                String sql = "DELETE  FROM DESPESA WHERE id = ?";
                PreparedStatement pt = null;
              
                Connection conn = DbConnection.getConexao();
                pt = conn.prepareStatement(sql);
                pt.setInt(1, despesa.getId());
                        
                if(pt.executeUpdate() > 0){
                    return true;
                } else {
                    return false;
                }
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
    




}
