package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

import java.sql.Connection;

public class RegisteredUserController extends DashboardController {

	public RegisteredUserController(Stage primaryStage, Connection connection, User user) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		System.out.println(user.getUser_id());

		profilemodel.setUser(user);

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		SongPlayerController player = new SongPlayerController(songplayermodel, connection);
	}

	@Override
	public void viewProfile() {
		ShowProfileController profileview = new ShowProfileController(profilemodel, connection);
	}

	@Override
	public void uploadSong() {
		UploadSongController upload = new UploadSongController(profilemodel, librarymodel, connection);
	}


	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
