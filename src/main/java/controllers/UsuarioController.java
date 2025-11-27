package controllers;

import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifpr.pgua.eic.tads.utils.model.Usuario;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;

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

}
