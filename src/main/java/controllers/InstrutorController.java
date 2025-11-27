package controllers;

import io.javalin.http.Handler;

public class InstrutorController {
    public Handler dashinstrutor = ctx -> {
        ctx.render("instrutor.html");
    };
}
