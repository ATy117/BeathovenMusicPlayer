package app;

import controller.MasterController;
import dbservice.UserDAO;
import dbservice.UserDAODB;
import dbservice.dbConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model_rework.User;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        MasterController controller = new MasterController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
