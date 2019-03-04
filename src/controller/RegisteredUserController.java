package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

public class RegisteredUserController extends DashboardController {

	public RegisteredUserController(Stage primaryStage, User user) {

		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		profilemodel.setUser(user);

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		SongPlayerController player = new SongPlayerController(songplayermodel);
	}

	@Override
	public void viewProfile() {
		ShowProfileController profileview = new ShowProfileController(profilemodel);
	}

	@Override
	public void uploadSong() {
		UploadSongController upload = new UploadSongController();
	}


	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
