/*
    References:
    Guava Library: https://github.com/google/guava
    JFoenix: https://github.com/jfoenixadmin/JFoenix
    ControlsFX: https://github.com/controlsfx/controlsfx
    AnimateFX: https://github.com/Typhon0/AnimateFX
    JDBC: https://dev.mysql.com/downloads/connector/j/

    MUSIC PLAYER: https://docs.oracle.com/javase/8/javafx/media-tutorial/playercontrol.htm
 */


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
        LoginController startup = new LoginController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
