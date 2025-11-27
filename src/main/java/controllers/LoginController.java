package controllers;

import br.edu.ifpr.pgua.eic.tads.utils.model.Usuario;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class LoginController {

    private UsuarioDAO usuarioDAO;

    public LoginController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Handler autenticar = (Context ctx) -> {
        String cpf = ctx.formParam("CPF");
        String senha = ctx.formParam("senha");

        try {
            Usuario usuario = usuarioDAO.buscarPorCpfESenha(cpf, senha);

            if (usuario != null) {
                String tipo = usuario.getTipo().toLowerCase();

                switch (tipo) {
                    case "adm":
                        ctx.redirect("/adm");
                        break;
                    case "instrutor":
                        ctx.redirect("/instrutor");
                        break;
                    default:
                        ctx.redirect("/aluno");
                        break;
                }
            } else {
                ctx.result("Erro: CPF ou Senha inválidos.");
            }

        } catch (Exception e) {
            ctx.result("Erro interno no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    };
}
