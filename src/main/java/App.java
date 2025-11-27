import java.sql.SQLException;

import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;
import br.edu.ifpr.pgua.eic.tads.utils.model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;
import controllers.AdminController;
import controllers.LoginController;
import controllers.UsuarioController;

public class App {
    public static void main(String[] args) {

        var app = JavalinUtils.makeApp(7070);

        try {

            UsuarioDAO usuarioDAO = new UsuarioDAO(FabricaConexoes.obterConexao());
            UsuarioController usuarioController = new UsuarioController(usuarioDAO);
            LoginController loginController = new LoginController(usuarioDAO);

            app.get("/", new controllers.IndexController().get);
            app.post("/home", loginController.autenticar);

            // Rotas administrativas
            app.get("/adm", new AdminController().dashadmin);
            app.get("/adm/usuarios", usuarioController.listarUsuarios);
            app.get("/adm/usuarios/deletar/{id}", usuarioController.deletarUsuario);
            app.get("/adm/usuarios/editar/{id}", usuarioController.exibirFormularioEdicao);
            app.post("/adm/usuarios/editar", usuarioController.editarUsuario);

            app.get("/instrutor", new controllers.InstrutorController().dashinstrutor);
            app.get("/aluno", new controllers.AlunoController().dashaluno);

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}