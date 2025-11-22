import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;

public class App {
    public static void main(String[] args) {

        var app = JavalinUtils.makeApp(7070);

        app.get("/", new controllers.IndexController().get);
    }
}