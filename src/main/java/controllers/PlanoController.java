package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifpr.pgua.eic.tads.utils.model.Plano;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.PlanoDAO;
import io.javalin.http.Handler;

public class PlanoController {
    private PlanoDAO dao;

    public PlanoController(PlanoDAO dao) {
        this.dao = dao;
    }

    public Handler listarPlanos = ctx -> {
        List<Plano> lista = dao.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("planos", lista);

        ctx.render("pages/listar-planos.html", model);
    };

    public Handler exibirFormularioCriacao = ctx -> {
        ctx.render("pages/criar-plano.html");
    };

    public Handler criarPlano = ctx -> {
        String nome = ctx.formParam("nome");
        Double valor = Double.valueOf(ctx.formParam("valor"));
        String beneficios = ctx.formParam("beneficios");

        Plano plano = new Plano(nome, valor, beneficios);
        boolean sucesso = dao.create(plano);

        if (sucesso) {
            ctx.redirect("/adm/planos");
        } else {
            ctx.result("Erro ao criar plano");
        }
    };

    public Handler exibirFormularioEdicao = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Plano plano = dao.findById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("plano", plano);

        ctx.render("pages/editar-plano.html", model);
    };

    public Handler editarPlano = ctx -> {
        int id = Integer.parseInt(ctx.formParam("id"));
        String nome = ctx.formParam("nome");
        Double valor = Double.valueOf(ctx.formParam("valor"));
        String beneficios = ctx.formParam("beneficios");

        Plano plano = new Plano(id, nome, valor, beneficios);
        boolean sucesso = dao.update(plano);

        if (sucesso) {
            ctx.redirect("/adm/planos");
        } else {
            ctx.result("Erro ao editar plano");
        }
    };

    public Handler deletarPlano = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean sucesso = dao.delete(id);

        if (sucesso) {
            ctx.redirect("/adm/planos");
        } else {
            ctx.result("Erro ao deletar plano");
        }
    };
}
