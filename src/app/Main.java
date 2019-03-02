package app;

import controller.WelcomeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        WelcomeController startup = new WelcomeController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
