package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SongPlayerView extends View {

	public SongPlayerView(MasterController controller, Stage stage) throws IOException {

		this.controller = controller;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayerTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Music Player");
		stage.setScene(scene);
	}

	public void playpause (ActionEvent actionEvent) {

	}

	public void prevSong(ActionEvent actionEvent) {
		// if 1 click or 2 click, diff response

	}

	public void nextSong(ActionEvent actionEvent) {

	}

	public void shuffle(ActionEvent actionEvent) {

	}

	public void repeat(ActionEvent actionEvent) {

	}

	public void goToDuration(ActionEvent actionEvent) {

	}

	@Override
	public void Update(){}
}
