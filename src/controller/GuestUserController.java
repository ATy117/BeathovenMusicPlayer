package controller;

import dbservice.UserDAO;
import dbservice.UserDAODB;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

import java.sql.Connection;
import java.util.ArrayList;

public class GuestUserController extends DashboardController {

	private static int counter = 0;

	public GuestUserController(Stage primaryStage, Connection connection) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		this.primaryStage = primaryStage;
		playerStage = new Stage();
		profileStage = new Stage();
		uploadStage = new Stage();

		UserDAO UD = new UserDAODB(connection);
		GuestUserBuilder GUB = new GuestUserBuilder();
		String username = "username" + counter;
		String password = "password" + counter;
		GuestUser guestUser = GUB
				.withAvatar(null)
				.withFirstName("firstname" + counter)
				.withLastName("lastname" + counter)
				.withUsername(username)
				.withPassword(password)
				.build();

		counter++;
		UD.addUser(guestUser);
		RegisteredUser GU = (RegisteredUser) UD.getUser(username, password);
		System.out.println(GU.getUser_id());
		guestUser.setUser_id(GU.getUser_id());


		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);
		profilemodel.setUser(guestUser);

		librarymodel.setSongList(new ArrayList<>());
		librarymodel.setPlaylistList(new ArrayList<>());
		librarymodel.setAlbumList(new ArrayList<>());

		SongPlayerController player = new SongPlayerController(songplayermodel, connection, playerStage);
	}

	@Override
	public void viewProfile() {
		RegisterController register = new RegisterController(profileStage, connection);
	}

	public void logout(){
		UserDAO UD = new UserDAODB(connection);
		UD.deleteUser(profilemodel.getUser().getUser_id());
		playerStage.close();
		uploadStage.close();
		profileStage.close();
		LoginController login = new LoginController(primaryStage);
	}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
