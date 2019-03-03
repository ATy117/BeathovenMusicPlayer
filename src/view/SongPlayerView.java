package view;

import com.jfoenix.controls.JFXButton;
import controller.StageManager;
import controller.SongPlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SongPlayerView{

	Stage playerStage;
	SongPlayerController controller;

	@FXML public JFXButton playBtn;
	@FXML public JFXButton ffBtn;
	@FXML public JFXButton rewindBtn;
	@FXML public JFXButton shuffleBtn;
	@FXML public JFXButton repeatBtn;

	public SongPlayerView (Stage playerStage, SongPlayerController controller) {

		this.playerStage = playerStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayerTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(playerStage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven Music Player");
	}



	public void playPause(ActionEvent actionEvent) {
		controller.playPauseSong();
	}

	public void nextSong(ActionEvent actionEvent) {
		/*if ffBtn double click*/
		controller.playNextSong();
		/*else if ffBtn click once*/
		controller.fastForward();
	}

	public void prevSong(ActionEvent actionEvent) {
		/* if rewindBtn doublic click*/
		controller.playPrevSong();
		/*else if rewindBtn clicked once*/
		controller.rewind();
	}

	public void shuffle(ActionEvent actionEvent) {
		controller.shuffle();
	}

	public void repeat(ActionEvent actionEvent) {
		controller.repeat();
	}

	public void goToDuration(MouseEvent mouseEvent) {
		controller.duration();
	}
}
