package br.edu.ifpr.pgua.eic.tads.utils.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class FabricaConexoes {
    
    
    private static final Dotenv dotenv = Dotenv.load(); 

    
    private static final String DB_URL_BASE = dotenv.get("DB_URL"); 
    private static final String DB_NAME = dotenv.get("DB_NAME"); 
    private static final String DB_USER = dotenv.get("DB_USER"); 
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD"); 
    
    
    private static final String FULL_DB_URL = DB_URL_BASE + "/" + DB_NAME;

    public static Connection obterConexao() throws SQLException {
        try {
            
            return DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        }
    }
}