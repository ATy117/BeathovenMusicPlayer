package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.FileUploader;
import controller.MP3Uploader;
import controller.StageManager;
import controller.UploadSongController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.File;

public class UploadSongsView{

	Stage primaryStage;
	UploadSongController controller;
	File musicfile = null;

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
		FileUploader file = new MP3Uploader(primaryStage);
		musicfile = file.getUploadedFile();
	}


	public void addEditSongDetails(javafx.event.ActionEvent actionEvent) {
		String songTitle = titleField.getText();
		String artistName = artistField.getText();
		String genre = genreField.getValue().toString();
		String year = yearField.getText();
		String album = albumField.getText();

		String songCheck = songTitle.replaceAll("\\s+", "");
		String artistCheck = artistName.replaceAll("\\s+", "");
		String genreCheck = genre.replaceAll("\\s+", "");
		String yearCheck = year.replaceAll("\\s+", "");

		if (songCheck.equals("") || artistCheck.equals("") || genreCheck.equals("") || yearCheck.equals("") || musicfile == null){
			System.out.println("make sure all fields are filled");
	    	primaryStage.close();
		} else {
			primaryStage.close();
			controller.uploadSong(songTitle, artistName, album, genre, year, musicfile);
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
