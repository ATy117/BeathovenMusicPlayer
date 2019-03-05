package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

import java.sql.Connection;

public class RegisteredUserController extends DashboardController {

<<<<<<< HEAD

=======
	private Stage primaryStage;
>>>>>>> parent of 2647d75... nak ng tokwa

	public RegisteredUserController(Stage primaryStage, Connection connection, User user) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

<<<<<<< HEAD
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

		mystages.add(primaryStage);
		mystages.add(profileStage);
		mystages.add(playerStage);
		mystages.add(uploadStage);

=======
		this.primaryStage = primaryStage;
		primaryStage.setOnHidden(e -> Platform.exit());
		
>>>>>>> parent of 2647d75... nak ng tokwa

		System.out.println(user.getUser_id());

		profilemodel.setUser(user);

		View dashboard = new DashboardView(mystages.get(0), songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		SongPlayerController player = new SongPlayerController(playerStage, songplayermodel, connection);
	}

	@Override
	public void viewProfile() {
<<<<<<< HEAD
		ShowProfileController profileview = new ShowProfileController(mystages.get(0), mystages.get(1), profilemodel, connection);
=======
		ShowProfileController profileview = new ShowProfileController(primaryStage, profilemodel, connection);
>>>>>>> parent of 2647d75... nak ng tokwa
	}



	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
