package App;

import App.Controllers.ControllersManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Stage prStage;
    ControllersManager manager;

    @Override
    public void start(Stage prStage) throws Exception {
        this.prStage = prStage;
        prStage.setTitle("KEWBR Quiz");

        manager = new ControllersManager(this);

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/MainMenu.fxml"));
            loader.setController(manager.getMainMenu());
            AnchorPane rootPane = loader.load();
            prStage.setScene(new Scene(rootPane));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        prStage.show();
    }

    public final void close() {
        prStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
