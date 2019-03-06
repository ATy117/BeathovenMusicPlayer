package app;

import controller.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
        });
        LoginController startup = new LoginController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
