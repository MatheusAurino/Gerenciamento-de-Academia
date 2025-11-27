package controllers;

import io.javalin.http.Handler;

public class AlunoController {
    public Handler dashaluno = ctx -> {
        ctx.render("aluno.html");
    };
}
