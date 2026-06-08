package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final Connection database;
    public Conexao(){
        try{
            database = DriverManager.getConnection("jdbc:sqlite:nosso_banco.db");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
