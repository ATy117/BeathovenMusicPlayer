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
	protected List<Stage> mystages;

	public abstract void viewProfile();

	public void uploadSong() {
		UploadSongController upload = new UploadSongController(profilemodel, mystages.get(3), librarymodel, connection);
	}

	public abstract void sayHi();

}
