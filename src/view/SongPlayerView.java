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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import model_rework.Album;
import model_rework.SongPlayerModel;

import java.io.File;


public class SongPlayerView extends View{

	@FXML public Label titleText;
	@FXML public Label artistText;
	@FXML public Label albumText;
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
	@FXML public JFXButton volumeDown;
	@FXML public JFXButton volumeUp;
	@FXML public AnchorPane songPlayerAnchor;
	private Duration duration;
	private final boolean repeat = false;
	private boolean stopRequested = false;
	private boolean atEndOfMedia = false;
	private Media currentSongMedia;
	private ImageView playView;
	private ImageView pauseView;
	private ImageView replayClickedView;
	private ImageView replayView;
	private ImageView shuffleView;
	private ImageView shuffleClickedView;
	private double volume;


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

		playView = new ImageView(play);
		playView.setFitHeight(30);
		playView.setFitWidth(25);

		Image pause = new Image("/resources/pause.png");
		pauseView = new ImageView(pause);
		pauseView.setFitHeight(30);
		pauseView.setFitWidth(25);

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

		shuffleView = new ImageView(shuffle);
		shuffleView.setFitHeight(16);
		shuffleView.setFitWidth(20);

		Image shuffleClicked = new Image("/resources/ShuffleClick.png");
		shuffleClickedView = new ImageView(shuffleClicked);
		shuffleClickedView.setFitHeight(16);
		shuffleClickedView.setFitWidth(20);

		shuffleBtn.setGraphic(shuffleView);

		Image replay = new Image("/resources/repeat.png");
		replayView = new ImageView(replay);
		replayView.setFitHeight(16);
		replayView.setFitWidth(20);

		Image replayClicked = new Image("/resources/repeatClick.png");
		replayClickedView = new ImageView(replayClicked);
		replayClickedView.setFitHeight(16);
		replayClickedView.setFitWidth(20);

		repeatBtn.setGraphic(replayView);

		Image down = new Image("resources/volDown.png");
		ImageView downView = new ImageView(down);
		volumeDown.setGraphic(downView);
		downView.setFitHeight(17);
		downView.setFitWidth(15);

		Image up  = new Image("resources/volUp.png");
		ImageView upView = new ImageView(up);
		volumeUp.setGraphic(upView);
		upView.setFitWidth(15);
		upView.setFitHeight(17);

		titleText.setMaxWidth(Double.MAX_VALUE);
		titleText.setAlignment(Pos.CENTER);
		artistText.setMaxWidth(Double.MAX_VALUE);
		artistText.setAlignment(Pos.CENTER);
		albumText.setMaxWidth(Double.MAX_VALUE);
		albumText.setAlignment(Pos.CENTER);

        titleText.setText("No Song Playing");
        artistText.setText("");
        albumText.setText("");
        genreText.setText("");
        timeStamp.setText("");

		songPlayerAnchor.getStylesheets().add("view/theme.css");
		slider.getStyleClass().add("jfx-slider");

		titleText.setText("No Song Playing");
		artistText.setText("");
		albumText.setText("");
		genreText.setText("");
		timeStamp.setText("");
		volume =0.5;
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
			artistText.setText("by " + songplayermodel.getCurrentSong().getArtist_name());
			genreText.setText(songplayermodel.getCurrentSong().getGenre());

			Image albpic = controller.getImageFromAlbum(songplayermodel.getCurrentSong().getAlbum_id());
			songPic.setFill(new ImagePattern(albpic));

			Album album = controller.getAlbumOfSong(songplayermodel.getCurrentSong().getAlbum_id());

			if (album != null) {
				albumText.setText("Album: " + album.getName());
			}
			else {
				albumText.setText("Album: None");
			}

			currentSongMedia = new Media(songplayermodel.getCurrentSong().getSong_URL().toURI().toString());
			mp3player = new MediaPlayer(currentSongMedia);
			toggleToPlay();
			setPlayerFunctionality();
		}

	}

	public void nextSong(ActionEvent actionEvent) {

		if (songplayermodel.getCurrentSong() != null) {
			controller.playNextSong();
		}
	}

	public void prevSong(ActionEvent actionEvent) {
		if (songplayermodel.getCurrentSong()!= null) {
			controller.playPrevSong();
		}
	}

	public void shuffle(ActionEvent actionEvent) {
		if(controller.toggleShuffle()) {
			shuffleBtn.setGraphic(shuffleClickedView);
		}
		else {
			shuffleBtn.setGraphic(shuffleView);
		}
	}

	public void repeat(ActionEvent actionEvent) {
		if(controller.toggleRepeat()) {
			repeatBtn.setGraphic(replayClickedView);
		}
		else {
			repeatBtn.setGraphic(replayView);
		}
	}

	public void endPlayer() {
		if (mp3player != null) {
			mp3player.stop();
		}
	}

	private void toggleToPlay() {
		mp3player.play();
		playBtn.setGraphic(pauseView);
	}

	private void toggleToPause() {
		mp3player.pause();
		playBtn.setGraphic(playView);
	}

	public void turnVolumeUp(ActionEvent actionEvent){
		if (mp3player!= null) {
			if (volume < 1) {
				volume += 0.1;
				System.out.println("VolumeUp");
				mp3player.setVolume(volume);
			}
		}

	}

	public void turnVolumeDown(ActionEvent actionEvent){

		if (mp3player!=null) {
			if (volume > 0) {
				volume -= 0.1;
				System.out.println("VolumeDown");
				mp3player.setVolume(volume);
			}
		}
	}

	private void setPlayerFunctionality() {
		mp3player.currentTimeProperty().addListener(new InvalidationListener()
		{
			public void invalidated(Observable ov) {
				updateValues();
			}
		});

		mp3player.setOnPlaying(new Runnable() {
			public void run() {
				if (stopRequested) {
					toggleToPause();
					stopRequested = false;
				} else {

				}
			}
		});

		mp3player.setOnPaused(new Runnable() {
			public void run() {

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
				playBtn.setGraphic(playView);
				controller.playNextSong();
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


	public void playPause(ActionEvent actionEvent) {

		if (songplayermodel.getCurrentSong() != null ){
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
				toggleToPlay();
			} else {
				toggleToPause();
			}
		}
	}
}
