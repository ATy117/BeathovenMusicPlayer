package controller;

import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

public class RegisteredUserController extends DashboardController {

	DashboardModel model;


	public RegisteredUserController(Stage primaryStage, User user) {
		this.model = new DashboardModel(user);
		this.primaryStage = primaryStage;

		View dashboard = new DashboardView(primaryStage, model, this);
		model.Attach(dashboard);

		playerStage = new Stage();
		SongPlayerController player = new SongPlayerController(playerStage);
	}

	@Override
	public void viewProfile() {
		ShowProfileController profileview = new ShowProfileController(primaryStage);
	}

	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
