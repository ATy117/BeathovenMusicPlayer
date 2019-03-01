package view;

import controller.MasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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



	@Override
	public void Update(){}

	public void playPause(ActionEvent actionEvent) {
	}

	public void nextSong(ActionEvent actionEvent) {
	}

	public void prevSong(ActionEvent actionEvent) {
	}

	public void shuffle(ActionEvent actionEvent) {
	}

	public void repeat(ActionEvent actionEvent) {
	}

	public void goToDuration(MouseEvent mouseEvent) {
	}
}
