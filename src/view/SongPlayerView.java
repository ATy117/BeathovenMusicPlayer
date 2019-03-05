package view;

import com.jfoenix.controls.JFXButton;
import controller.StageManager;
import controller.SongPlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.SongPlayerModel;

public class SongPlayerView extends View{

	private Stage playerStage;
	private SongPlayerController controller;
	private SongPlayerModel songplayermodel;

	@FXML public JFXButton playBtn;
	@FXML public JFXButton ffBtn;
	@FXML public JFXButton rewindBtn;
	@FXML public JFXButton shuffleBtn;
	@FXML public JFXButton repeatBtn;


	public SongPlayerView (Stage playerStage, SongPlayerModel songplayermodel, SongPlayerController controller) {

		this.playerStage = playerStage;
		this.controller = controller;
		this.songplayermodel = songplayermodel;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayerTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(playerStage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven Music Player");

		Image play = new Image("resources/play.png");
		ImageView playView = new ImageView(play);
		playView.setFitHeight(30);
		playView.setFitWidth(25);
		playBtn.setGraphic(playView);

		Image ff = new Image("resources/ff.png");
		ImageView ffView = new ImageView(ff);
		ffView.setFitHeight(28);
		ffView.setFitWidth(40);
		ffBtn.setGraphic(ffView);

		Image rewind = new Image("resources/rewind.png");
		ImageView rewindView = new ImageView(rewind);
		rewindView.setFitHeight(28);
		rewindView.setFitWidth(40);
		rewindBtn.setGraphic(rewindView);
	}

	@Override
	public void Update(){}



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
