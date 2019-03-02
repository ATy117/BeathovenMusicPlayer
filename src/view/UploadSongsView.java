package view;

import controller.UploadSongController;
import controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UploadSongsView{

	Stage primaryStage;
	UploadSongController controller;

	public UploadSongsView (Stage primaryStage, UploadSongController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadSongsTemplate.fxml"));
		loader.setController(this);
		Parent root = null;

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);

		primaryStage.setTitle("Upload Songs");
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public void uploadAlbumCover(javafx.event.ActionEvent actionEvent) {
	}

	public void uploadSongFile(javafx.event.ActionEvent actionEvent) {
	}

	public void addEditSongDetails(javafx.event.ActionEvent actionEvent) {
	}
}
