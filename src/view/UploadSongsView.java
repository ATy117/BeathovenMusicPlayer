package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class UploadSongsView extends View{

	public UploadSongsView (MasterController controller, Stage stage) throws IOException {

		this.controller = controller;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadSongsTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Upload songs");
		stage.setScene(scene);
	}

	public void uploadAlbumCover (ActionEvent actionEvent) {

	}

	public void uploadSongFile(ActionEvent actionEvent) {

	}

	public void addEditSongDetails(ActionEvent actionEvent) {

	}




	@Override
	public void Update(){}
}
