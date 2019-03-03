package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

public class GuestUserController extends DashboardController {

	public GuestUserController(Stage primaryStage) {
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		this.primaryStage = primaryStage;

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);

		playerStage = new Stage();
		SongPlayerController player = new SongPlayerController(playerStage, songplayermodel);
	}

	@Override
	public void viewProfile() {

	}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
