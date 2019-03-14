package controller;

import dbservice.UserDAO;
import dbservice.UserDAODB;
import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public class GuestUserDashboardController extends DashboardController {

	private static int counter = 0;

	public GuestUserDashboardController(Stage primaryStage, Connection connection) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		this.primaryStage = primaryStage;
		this.primaryStage.setOnCloseRequest(e -> {
			UserDAO UD = new UserDAODB(connection);
			UD.deleteUser(profilemodel.getUser().getUser_id());
			Platform.exit();
		});
		playerStage = new Stage();
		profileStage = new Stage();
		uploadStage = new Stage();

		UserDAO UD = new UserDAODB(connection);
		GuestUserBuilder GUB = new GuestUserBuilder();
		String username = "username" + counter;
		String password = "password" + counter;
		GuestUser guestUser = GUB
				.withAvatar(new File("src/resources/useryellowbluedefaultpic.png"))
				.withFirstName("firstname" + counter)
				.withLastName("lastname" + counter)
				.withUsername(username)
				.withPassword(password)
				.build();

		counter++;
		UD.addUser(guestUser);
		RegisteredUser GU = (RegisteredUser) UD.getUser(username, password);
		guestUser.setUser_id(GU.getUser_id());

		profilemodel.setUser(guestUser);
		librarymodel.setSongList(new ArrayList<>());
		librarymodel.setPlaylistList(new ArrayList<>());
		librarymodel.setAlbumList(new ArrayList<>());

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		player = new SongPlayerController(songplayermodel, connection, playerStage);
	}

	@Override
	public void viewProfile() {
		UserRegisterController register = new GuestUserRegisterController(profilemodel.getUser().getUser_id(), profileStage, playerStage, uploadStage, primaryStage, connection);
	}

	public void logout(){
		UserDAO UD = new UserDAODB(connection);
		UD.deleteUser(profilemodel.getUser().getUser_id());
		playerStage.close();
		uploadStage.close();
		profileStage.close();
		player.endPlayer();
		LoginController login = new LoginController(primaryStage);
	}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
