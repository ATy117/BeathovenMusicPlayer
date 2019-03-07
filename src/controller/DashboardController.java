package controller;

import javafx.stage.Stage;
import model_rework.*;

import java.sql.Connection;

public abstract class DashboardController {

	protected Connection connection;
	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;

	protected Stage uploadStage;
	protected Stage playerStage;
	protected Stage profileStage;
	protected Stage primaryStage;

	public abstract void viewProfile();

	public void uploadSong() {
		UploadSongController upload = new UploadSongController(profilemodel, librarymodel, connection, playerStage);
	}

	public void showSongPlayer() {
		SongPlayerController player = new SongPlayerController(songplayermodel, connection, playerStage);
	}

	public abstract void sayHi();

	public abstract void logout();

}
