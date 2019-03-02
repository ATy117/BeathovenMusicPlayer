package controller;

import javafx.stage.Stage;
import model_rework.DashboardModel;
import model_rework.User;
import view.DashboardView;
import view.View;

public class GuestUserController extends DashboardController {

	DashboardModel model;

	public GuestUserController(Stage primaryStage, User user) {
		this.model = new DashboardModel(user);
		this.primaryStage = primaryStage;
		View dash = new DashboardView(primaryStage, model, this);
		model.Attach(dash);

		playerStage = new Stage();
		SongPlayerController player = new SongPlayerController(playerStage);
	}

	@Override
	public void viewProfile() {

	}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
