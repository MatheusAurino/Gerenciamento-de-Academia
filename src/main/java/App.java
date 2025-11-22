import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;
import controllers.LoginController; 

public class App {
    public static void main(String[] args) {

        var app = JavalinUtils.makeApp(7070);

       
        app.get("/", new controllers.IndexController().get);
       
        app.post("/home", new LoginController().autenticar); 
    }
}