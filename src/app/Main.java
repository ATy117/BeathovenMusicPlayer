package app;

import controller.LoginController;
import controller.SongPlayerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.SongPlayerModel;

import java.sql.Connection;

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
