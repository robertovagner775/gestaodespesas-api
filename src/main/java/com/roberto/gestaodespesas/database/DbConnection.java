package com.roberto.gestaodespesas.database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;


@Configuration
public class DbConnection {
    
  
    public static final String user = "root";

    public static final String url = "jdbc:mysql://localhost/gestaodespesas";

    public static final String password = "";

    public static Connection conn;



    public static Connection getConexao() {

        try{
            if(conn == null) {
                conn =   DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
}
