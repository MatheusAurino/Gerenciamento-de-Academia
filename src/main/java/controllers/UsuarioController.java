package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifpr.pgua.eic.tads.utils.model.Usuario;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;
import io.javalin.http.Handler;

public class UsuarioController {

    private UsuarioDAO dao;

    public UsuarioController(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Handler listarUsuarios = ctx -> {
        List<Usuario> lista = dao.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("usuarios", lista);

        ctx.render("pages/usuarios.html", model);
    };

    public Handler deletarUsuario = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean sucesso = dao.delete(id);

            if (sucesso) {
                ctx.redirect("/adm/usuarios");
            } else {
                ctx.result("Falha ao deletar o usuário.");
            }
        } catch (NumberFormatException e) {
            ctx.result("ID inválido: " + e.getMessage());
        }
    };

    public Handler exibirFormularioEdicao = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = dao.findById(id);

            if (usuario != null) {
                Map<String, Object> model = new HashMap<>();
                model.put("usuario", usuario);
                ctx.render("pages/editar-usuario.html", model);
            } else {
                ctx.result("Usuário não encontrado.");
            }
        } catch (NumberFormatException e) {
            ctx.result("ID inválido: " + e.getMessage());
        }
    };

    public Handler editarUsuario = ctx -> {
        try {
            String id = ctx.formParam("id");
            String nome = ctx.formParam("nome");
            String cpf = ctx.formParam("cpf");
            String senha = ctx.formParam("senha");
            String tipo = ctx.formParam("tipo");

            Usuario usuario = new Usuario(id, nome, cpf, senha, tipo);
            boolean sucesso = dao.update(usuario);

            if (sucesso) {
                ctx.redirect("/adm/usuarios");
            } else {
                ctx.result("Falha ao editar o usuário.");
            }
        } catch (Exception e) {
            ctx.result("Erro ao processar edição: " + e.getMessage());
        }
    };

}
