package controller;

import javafx.stage.Stage;
import model_rework.LibraryModel;
import model_rework.ProfileModel;
import view.UploadSongsView;

import java.sql.Connection;

public class UploadSongController {

	private Stage uploadStage;
	private ProfileModel profilemodel;
	private LibraryModel librarymodel;

	public UploadSongController(ProfileModel profilemodel, LibraryModel librarymodel, Connection connection) {

		this.profilemodel = profilemodel;
		this.librarymodel = librarymodel;

		uploadStage = new Stage();

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
    }

    
	public boolean confirmDetails() {
		uploadStage.close();
		return true;
	}
}
