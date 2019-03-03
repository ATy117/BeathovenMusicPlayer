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
		this.primaryStage = primaryStage;

		profilemodel.setUser(user);

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		playerStage = new Stage();
		SongPlayerController player = new SongPlayerController(playerStage, songplayermodel);
	}

	@Override
	public void viewProfile() {
		ShowProfileController profileview = new ShowProfileController(primaryStage, profilemodel);
	}

	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
