package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model_rework.*;
import view.DashboardView;
import view.View;

import java.sql.Connection;

public class GuestUserController extends DashboardController {

<<<<<<< HEAD
=======
	Stage primaryStage;

>>>>>>> parent of 2647d75... nak ng tokwa

	public GuestUserController(Stage primaryStage, Connection connection) {
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		Stage profileStage = new Stage();
		Stage playerStage = new Stage();
		Stage uploadStage = new Stage();

		primaryStage.setOnHidden(e -> Platform.exit());

		mystages.add(primaryStage);
		mystages.add(profileStage);
		mystages.add(playerStage);
		mystages.add(uploadStage);

		this.connection = connection;

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);

<<<<<<< HEAD
		SongPlayerController player = new SongPlayerController(playerStage,songplayermodel, connection);
=======
		SongPlayerController player = new SongPlayerController(songplayermodel, connection);
>>>>>>> parent of 2647d75... nak ng tokwa
	}

	@Override
	public void viewProfile() {
		RegisterController register = new RegisterController(mystages.get(0), connection);
	}


	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
