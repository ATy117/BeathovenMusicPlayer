package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import controller.StageManager;
import controller.SongPlayerController;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model_rework.Album;
import model_rework.SongPlayerModel;

import java.io.File;


public class SongPlayerView extends View{

	@FXML public Text titleText;
	@FXML public Text artistText;
	@FXML public Text albumText;
	@FXML public Text genreText;
	@FXML public Circle songPic;
	private Stage playerStage;
	private SongPlayerController controller;
	private SongPlayerModel songplayermodel;
	private MediaPlayer mp3player;
	@FXML public JFXSlider slider;
	@FXML public Label timeStamp;
	@FXML public JFXButton playBtn;
	@FXML public JFXButton ffBtn;
	@FXML public JFXButton rewindBtn;
	@FXML public JFXButton shuffleBtn;
	@FXML public JFXButton repeatBtn;
	private Duration duration;
	private final boolean repeat = false;
	private boolean stopRequested = false;
	private boolean atEndOfMedia = false;
	private Media currentSongMedia;


	public SongPlayerView (Stage playerStage, SongPlayerModel songplayermodel, SongPlayerController controller) {

		this.playerStage = playerStage;
		this.controller = controller;
		this.songplayermodel = songplayermodel;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayerTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(playerStage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven Music Player");

		Image play = new Image("/resources/play.png");
		ImageView playView = new ImageView(play);
		playView.setFitHeight(30);
		playView.setFitWidth(25);
		playBtn.setGraphic(playView);

		Image ff = new Image("/resources/ff.png");
		ImageView ffView = new ImageView(ff);
		ffView.setFitHeight(28);
		ffView.setFitWidth(40);
		ffBtn.setGraphic(ffView);

		Image rewind = new Image("/resources/rewind.png");
		ImageView rewindView = new ImageView(rewind);
		rewindView.setFitHeight(28);
		rewindView.setFitWidth(40);
		rewindBtn.setGraphic(rewindView);

		Image shuffle = new Image("/resources/Shuffle.png");
		ImageView shuffleView = new ImageView(shuffle);
		shuffleView.setFitHeight(16);
		shuffleView.setFitWidth(20);
		shuffleBtn.setGraphic(shuffleView);

		Image replay = new Image("/resources/repeat.png");
		ImageView replayView = new ImageView(replay);
		replayView.setFitHeight(16);
		replayView.setFitWidth(20);
		repeatBtn.setGraphic(replayView);
	}



	protected void updateValues() {
		if (timeStamp != null && slider != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					Duration currentTime = mp3player.getCurrentTime();
					timeStamp.setText(formatTime(currentTime, duration));
					slider.setDisable(duration.isUnknown());
					if (!slider.isDisabled()
							&& duration.greaterThan(Duration.ZERO)
							&& !slider.isValueChanging()) {
						slider.setValue(currentTime.divide(duration).toMillis()
								* 100.0);
					}
				}
			});
		}
	}

	private static String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int)Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
				- elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int)Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60 -
					durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d",
						elapsedHours, elapsedMinutes, elapsedSeconds,
						durationHours, durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d",
						elapsedMinutes, elapsedSeconds,durationMinutes,
						durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours,
						elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d",elapsedMinutes,
						elapsedSeconds);
			}
		}

	}


	@Override
	public void Update(){

		if (mp3player != null) {
			mp3player.dispose();
			mp3player = null;
		}

		if (songplayermodel.getCurrentSong() != null) {

			titleText.setText(songplayermodel.getCurrentSong().getSong_name());
			artistText.setText(songplayermodel.getCurrentSong().getArtist_name());
			genreText.setText(songplayermodel.getCurrentSong().getGenre());

			Album album = controller.getAlbumOfSong(songplayermodel.getCurrentSong().getAlbum_id());

			if (album != null) {
				albumText.setText(album.getName());
			}
			else {
				albumText.setText("");
			}


			currentSongMedia = new Media(songplayermodel.getCurrentSong().getSong_URL().toURI().toString());
			mp3player = new MediaPlayer(currentSongMedia);
			mp3player.play();

			mp3player.currentTimeProperty().addListener(new InvalidationListener()
			{
				public void invalidated(Observable ov) {
					updateValues();
				}
			});

			mp3player.setOnPlaying(new Runnable() {
				public void run() {
					if (stopRequested) {
						mp3player.pause();
						stopRequested = false;
					} else {

					}
				}
			});

			mp3player.setOnPaused(new Runnable() {
				public void run() {
					System.out.println("onPaused");
				}
			});

			mp3player.setOnReady(new Runnable() {
				public void run() {
					duration = mp3player.getMedia().getDuration();
					updateValues();
				}
			});

			mp3player.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
			mp3player.setOnEndOfMedia(new Runnable() {
				public void run() {
					if (!repeat) {
						//playButton.setText(">");
						stopRequested = true;
						atEndOfMedia = true;
					}
				}
			});

			slider.valueProperty().addListener(new InvalidationListener() {
				public void invalidated(Observable ov) {
					if (slider.isValueChanging()) {
						// multiply duration by percentage calculated by slider position
						mp3player.seek(duration.multiply(slider.getValue() / 100.0));
					}
				}
			});
		}

	}


	public void playPause(ActionEvent actionEvent) {

		MediaPlayer.Status status = mp3player.getStatus();

		if (status == MediaPlayer.Status.UNKNOWN  || status == MediaPlayer.Status.HALTED)
		{
			// don't do anything in these states
			return;
		}

		if ( status == MediaPlayer.Status.PAUSED
				|| status == MediaPlayer.Status.READY
				|| status == MediaPlayer.Status.STOPPED)
		{
			// rewind the movie if we're sitting at the end
			if (atEndOfMedia) {
				mp3player.seek(mp3player.getStartTime());
				atEndOfMedia = false;
			}
			mp3player.play();
		} else {
			mp3player.pause();
		}

	}

	public void nextSong(ActionEvent actionEvent) {
		/*if ffBtn double click*/
		controller.playNextSong();
		/*else if ffBtn click once*/
		//ontroller.fastForward();
	}

	public void prevSong(ActionEvent actionEvent) {
		/* if rewindBtn doublic click*/
		controller.playPrevSong();
		/*else if rewindBtn clicked once*/
		//controller.rewind();
	}

	public void shuffle(ActionEvent actionEvent) {
		controller.shuffle();
	}

	public void repeat(ActionEvent actionEvent) {
		controller.repeat();
	}


	public void endPlayer() {
		mp3player.stop();
	}
}
