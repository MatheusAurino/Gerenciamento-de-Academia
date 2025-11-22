package controllers;

import br.edu.ifpr.pgua.eic.tads.utils.model.FabricaConexoes;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public Handler autenticar = (Context ctx) -> {
        
        String cpf = ctx.formParam("CPF"); 
        String senha = ctx.formParam("senha"); 

      
        
        String sql = "SELECT * FROM bd_aluno WHERE cpf = ? AND senha = ?"; 
        
        try (
            Connection conn = FabricaConexoes.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   
                
                    
                    ctx.result("Login realizado com sucesso! Bem-vindo, " + rs.getString("nome") + "!");
                } else {
                   
                    ctx.result("Erro: CPF ou Senha inválidos.");
                }
            }
            
        } catch (Exception e) {
            
            ctx.result("Erro interno no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    };
}