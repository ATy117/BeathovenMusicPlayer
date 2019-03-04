package controller;

import javafx.stage.Stage;
import model_rework.LibraryModel;
import model_rework.ProfileModel;
import view.UploadSongsView;

import java.io.File;
import java.sql.Connection;

public class RegisteredUploadController extends UploadController {

	public RegisteredUploadController(ProfileModel profilemodel, LibraryModel librarymodel, Connection connection) {

		this.profilemodel = profilemodel;
		this.librarymodel = librarymodel;
		this.connection = connection;

		uploadStage = new Stage();

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
	}

	@Override
	public boolean uploadSong(String song_title, String artist_name, String album_name, String genre, String year, File file) {
		return true;
	}

}
