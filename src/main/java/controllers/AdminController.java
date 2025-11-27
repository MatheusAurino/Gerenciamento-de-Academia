package controllers;

import io.javalin.http.Handler;

public class AdminController {
    public Handler dashadmin = ctx -> {
        ctx.render("adm.html");
    };
}
