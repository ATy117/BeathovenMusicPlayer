package view;

import controller.SceneManager;
import controller.SongPlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SongPlayerView{

	Stage playerStage;
	SongPlayerController controller;

	public SongPlayerView (Stage playerStage, SongPlayerController controller) {

		this.playerStage = playerStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayerTemplate.fxml"));
		loader.setController(this);

		SceneManager sm = new SceneManager(playerStage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven Music Player");
	}



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
