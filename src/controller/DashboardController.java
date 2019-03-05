package controller;

import javafx.stage.Stage;
import model_rework.*;

import java.sql.Connection;
import java.util.List;

public abstract class DashboardController {

	protected Connection connection;
	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;


	public abstract void viewProfile();

	public void uploadSong() {
		UploadSongController upload = new UploadSongController(profilemodel,librarymodel, connection);
	}

	public abstract void sayHi();

}
