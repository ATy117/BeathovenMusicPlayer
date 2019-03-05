package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

import java.sql.Connection;

public class RegisteredUserController extends DashboardController {

	private Stage primaryStage;


	public RegisteredUserController(Stage primaryStage, Connection connection, User user) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();


		Stage profileStage = new Stage();
		Stage playerStage = new Stage();
		Stage uploadStage = new Stage();

		primaryStage.setOnHidden(e -> {
			Platform.setImplicitExit(false);
			profileStage.close();
			playerStage.close();
			uploadStage.close();
			primaryStage.close();
		});


		this.primaryStage = primaryStage;
		primaryStage.setOnHidden(e -> Platform.exit());


		System.out.println(user.getUser_id());

		profilemodel.setUser(user);

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		SongPlayerController player = new SongPlayerController(playerStage, songplayermodel, connection);
	}

	@Override
	public void viewProfile() {


		ShowProfileController profileview = new ShowProfileController(primaryStage, profilemodel, connection);

	}



	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
