package controller;

import javafx.stage.Stage;
import view.UploadSongsView;

import java.io.IOException;

public class UploadSongController {
    Stage primaryStage;
    StageManager sm;

    public UploadSongController(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        UploadSongsView upload = new UploadSongsView(primaryStage, this);
    }

    public boolean getFile(){
        return true;
    }
}
