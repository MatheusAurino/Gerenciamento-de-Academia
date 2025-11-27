import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;
import br.edu.ifpr.pgua.eic.tads.utils.model.dao.UsuarioDAO;
import br.edu.ifpr.pgua.eic.tads.utils.model.FabricaConexoes; 
import controllers.AdminController;
import controllers.LoginController;
import controllers.UsuarioController; 
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        var app = JavalinUtils.makeApp(7070);

        try {
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(FabricaConexoes.obterConexao());
            UsuarioController usuarioController = new UsuarioController(usuarioDAO);
           
            app.get("/", new controllers.IndexController().get);
           
            app.post("/home", new LoginController().autenticar); 

            //Rotas administrativas
            app.get("/adm", new AdminController().dashadmin);
            app.get("/adm/usuarios", usuarioController.listarUsuarios);

            app.get("/instrutor", new controllers.InstrutorController().dashinstrutor);
            app.get("/aluno", new controllers.AlunoController().dashaluno);
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}