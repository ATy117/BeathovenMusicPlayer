package app;

import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginController startup = new LoginController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
