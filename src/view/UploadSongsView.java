package view;

import controller.StageManager;
import controller.UploadSongController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class UploadSongsView{

	Stage primaryStage;
	UploadSongController controller;

	public UploadSongsView (Stage primaryStage, UploadSongController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadSongsTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Upload songs");
	}

	public void uploadSongFile(javafx.event.ActionEvent actionEvent) {
		// file window
	}


	public void addEditSongDetails(javafx.event.ActionEvent actionEvent) {
		controller.confirmDetails();
	}
}
