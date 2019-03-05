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

	Stage primaryStage;


	public GuestUserController(Stage primaryStage, Connection connection) {
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();
		this.primaryStage = primaryStage;
		primaryStage.setOnHidden(e -> Platform.exit());

		this.connection = connection;

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);

		SongPlayerController player = new SongPlayerController(songplayermodel, connection);
	}

	@Override
	public void viewProfile() {
		RegisterController register = new RegisterController(primaryStage, connection);
	}


	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
