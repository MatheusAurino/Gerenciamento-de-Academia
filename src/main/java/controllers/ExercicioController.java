package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifpr.pgua.eic.tads.utils.model.Exercicio;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.ExercicioDAO;
import io.javalin.http.Handler;

public class ExercicioController {
    private ExercicioDAO dao;

    public ExercicioController(ExercicioDAO dao) {
        this.dao = dao;
    }

    public Handler listarExercicios = ctx -> {
        List<Exercicio> lista = dao.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("exercicios", lista);

        ctx.render("pages/listar-exercicios.html", model);
    };

    public Handler exibirFormularioCriacao = ctx -> {
        ctx.render("pages/criar-exercicio.html");
    };

    public Handler criarExercicio = ctx -> {
        String nome = ctx.formParam("nome");
        String grupoMuscular = ctx.formParam("grupo_muscular");

        Exercicio exercicio = new Exercicio(null, nome, grupoMuscular);
        boolean sucesso = dao.create(exercicio);

        if (sucesso) {
            ctx.redirect("/adm/exercicios");
        } else {
            ctx.result("Erro ao criar exercício");
        }
    };

    public Handler deletarExercicio = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean sucesso = dao.delete(id);

            if (sucesso) {
                ctx.redirect("/adm/exercicios");
            } else {
                ctx.result("Falha ao deletar o exercício.");
            }
        } catch (NumberFormatException e) {
            ctx.result("ID inválido: " + e.getMessage());
        }
    };

    public Handler exibirFormularioEdicao = ctx -> {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Exercicio exercicio = dao.findById(id);

            if (exercicio != null) {
                Map<String, Object> model = new HashMap<>();
                model.put("exercicio", exercicio);
                ctx.render("pages/editar-exercicio.html", model);
            } else {
                ctx.result("Exercício não encontrado.");
            }
        } catch (NumberFormatException e) {
            ctx.result("ID inválido: " + e.getMessage());
        }
    };

    public Handler editarExercicio = ctx -> {
        try {
            Integer id = Integer.parseInt(ctx.formParam("id"));
            String nome = ctx.formParam("nome");
            String grupoMuscular = ctx.formParam("grupo_muscular");

            Exercicio exercicio = new Exercicio(id, nome, grupoMuscular);
            boolean sucesso = dao.update(exercicio);

            if (sucesso) {
                ctx.redirect("/adm/exercicios");
            } else {
                ctx.result("Erro ao editar exercício");
            }
        } catch (Exception e) {
            ctx.result("Erro ao processar edição: " + e.getMessage());
        }
    };

}