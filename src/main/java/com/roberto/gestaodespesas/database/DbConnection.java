package com.roberto.gestaodespesas.database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;


@Configuration
public class DbConnection {
    
  
    public static final String user = "root";

    public static final String url = "jdbc:mysql://root:USdiSfybdjsGcLUQrzbDyYOSjzYBMEqP@roundhouse.proxy.rlwy.net:30852/railway";

    public static final String password = "USdiSfybdjsGcLUQrzbDyYOSjzYBMEqP";

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
