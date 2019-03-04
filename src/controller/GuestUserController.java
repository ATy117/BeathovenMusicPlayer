package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

public class GuestUserController extends DashboardController {

	public GuestUserController(Stage primaryStage) {
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);


		SongPlayerController player = new SongPlayerController(songplayermodel);
	}

	@Override
	public void viewProfile() {
		Stage reg = new Stage();
		RegisterController register = new RegisterController(reg);
	}

	@Override
	public void uploadSong() {}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
