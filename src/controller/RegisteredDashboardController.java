package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

import java.sql.Connection;

public class RegisteredDashboardController extends DashboardController {

	public RegisteredDashboardController(Stage primaryStage, Connection connection, User user) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();


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
		GuestUploadController upload = new GuestUploadController(profilemodel, librarymodel, connection);
	}


	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
