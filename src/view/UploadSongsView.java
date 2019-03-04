package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.StageManager;
import controller.UploadSongController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.File;

public class UploadSongsView{

	Stage primaryStage;
	UploadSongController controller;

	@FXML public JFXTextField titleField;
	@FXML public  JFXTextField artistField;
	@FXML public JFXTextField albumField;
	@FXML public JFXComboBox genreField;
	@FXML public JFXTextField yearField;
	@FXML public JFXButton uploadBtn;
	@FXML public JFXButton doneBtn;

	public UploadSongsView (Stage primaryStage, UploadSongController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadSongsTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Upload songs");
		init();
	}

	public void uploadSongFile(javafx.event.ActionEvent actionEvent) {
	    System.out.println("Upload button!");
		// file window
	}


	public void addEditSongDetails(javafx.event.ActionEvent actionEvent) {

	    File file = new File("resources/beathoven.png");
	    if(!(titleField.getText().isEmpty() || artistField.getText().isEmpty() || genreField.getValue().toString().isEmpty() || yearField.getText().isEmpty())) {
	        System.out.println("It is not Empty!");
	        primaryStage.close();
		    //controller.confirmDetails(titleField.getText(), artistField.getText(), genreField.getValue(), yearField.getText(), file);
        }
	}

	public void init(){
	    genreField.getItems().add("Pop");
	    genreField.getItems().add("Rock");
	    genreField.getItems().add("Country");
	    genreField.getItems().add("Hip-hop");
	    genreField.getItems().add("Classic");
	    genreField.getItems().add("Reggae");
	    genreField.getItems().add("Ballad");
	    genreField.getItems().add("EDM");
	    genreField.getItems().add("Folk");
    }

}
