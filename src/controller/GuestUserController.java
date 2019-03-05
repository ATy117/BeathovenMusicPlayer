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
	private Connection connection;

	public GuestUserController(Stage primaryStage, Connection connection) {
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

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
		GuestUser GU = (GuestUser) UD.getUser(username, password);
		System.out.println(GU.getUser_id());

		this.connection = connection;

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);
		profilemodel.setUser(GU);

		librarymodel.setSongList(new ArrayList<>());
		librarymodel.setPlaylistList(new ArrayList<>());
		librarymodel.setAlbumList(new ArrayList<>());

		SongPlayerController player = new SongPlayerController(songplayermodel, connection);
	}

	@Override
	public void viewProfile() {
		Stage reg = new Stage();
		RegisterController register = new RegisterController(reg, connection);
	}

	@Override
	public void uploadSong() {

	}


	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
