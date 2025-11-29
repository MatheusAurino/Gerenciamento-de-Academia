import java.sql.SQLException;

import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;
import br.edu.ifpr.pgua.eic.tads.utils.model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.ExercicioDAO;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.PlanoDAO;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;
import controllers.AdminController;
import controllers.ExercicioController;
import controllers.LoginController;
import controllers.PlanoController;
import controllers.UsuarioController;

public class App {
    public static void main(String[] args) {

        var app = JavalinUtils.makeApp(7070);

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(FabricaConexoes.obterConexao());
            UsuarioController usuarioController = new UsuarioController(usuarioDAO);
            LoginController loginController = new LoginController(usuarioDAO);

            PlanoDAO planoDAO = new PlanoDAO(FabricaConexoes.obterConexao());
            PlanoController planoController = new PlanoController(planoDAO);

            ExercicioDAO exercicioDAO = new ExercicioDAO(FabricaConexoes.obterConexao());
            ExercicioController exercicioController = new ExercicioController(exercicioDAO);

            app.get("/", new controllers.IndexController().get);
            app.post("/home", loginController.autenticar);

            // Rotas administrativas
            app.get("/adm", new AdminController().dashadmin);

            // Rotas de Usuários
            app.get("/adm/usuarios", usuarioController.listarUsuarios);
            app.get("/adm/usuarios/deletar/{id}", usuarioController.deletarUsuario);
            app.get("/adm/usuarios/editar/{id}", usuarioController.exibirFormularioEdicao);
            app.post("/adm/usuarios/editar", usuarioController.editarUsuario);
            app.post("/adm/usuarios/novo", usuarioController.criarUsuario);
            app.get("/adm/usuarios/novo", usuarioController.exibirFormularioCriacao);
            
            // Rotas de Planos
            app.get("/adm/planos", planoController.listarPlanos);
            app.get("/adm/planos/novo", planoController.exibirFormularioCriacao);
            app.post("/adm/planos/criar", planoController.criarPlano);
            app.get("/adm/planos/editar/{id}", planoController.exibirFormularioEdicao);
            app.post("/adm/planos/editar", planoController.editarPlano);
            app.get("/adm/planos/deletar/{id}", planoController.deletarPlano);

            // Rotas de Exercícios (Admin)
            app.get("/adm/exercicios", exercicioController.listarExercicios);
            app.get("/adm/exercicios/novo", exercicioController.exibirFormularioCriacao);
            app.post("/adm/exercicios/criar", exercicioController.criarExercicio);
            app.get("/adm/exercicios/editar/{id}", exercicioController.exibirFormularioEdicao);
            app.post("/adm/exercicios/editar", exercicioController.editarExercicio);
            app.get("/adm/exercicios/deletar/{id}", exercicioController.deletarExercicio);

            // Rotas de Exercícios (Instrutor)
            app.get("/instrutor/exercicios", exercicioController.listarExercicios);
            app.get("/instrutor/exercicios/novo", exercicioController.exibirFormularioCriacao);
            app.post("/instrutor/exercicios/criar", exercicioController.criarExercicio);
            app.get("/instrutor/exercicios/editar/{id}", exercicioController.exibirFormularioEdicao);
            app.post("/instrutor/exercicios/editar", exercicioController.editarExercicio);
            app.get("/instrutor/exercicios/deletar/{id}", exercicioController.deletarExercicio);

            app.get("/instrutor", new controllers.InstrutorController().dashinstrutor);
            app.get("/aluno", new controllers.AlunoController().dashaluno);

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}