package app;

import controller.MasterController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        MasterController controller = new MasterController(primaryStage);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
