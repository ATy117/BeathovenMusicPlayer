package controller;

import dbservice.UserDAO;
import dbservice.UserDAODB;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.View;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

public class GuestUserDashboardController extends DashboardController {

	private static int counter = 0;

	public GuestUserDashboardController(Stage primaryStage, Connection connection) {
		this.connection = connection;
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		this.primaryStage = primaryStage;
		this.primaryStage.setOnCloseRequest(e -> {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Are you sure?");
			alert.setHeaderText("Do you want to register first?");
			alert.setContentText("Not registering an account means all guest user info will be deleted.");

			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");

			alert.getButtonTypes().setAll(yes, no);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == yes){
				e.consume();
				UserRegisterController register = new GuestUserRegisterController(profilemodel.getUser().getUser_id(), profileStage, playerStage, uploadStage, primaryStage, connection, player);
			} else {
				UserDAO UD = new UserDAODB(connection);
				UD.deleteUser(profilemodel.getUser().getUser_id());
				Platform.exit();
			}

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
		UserRegisterController register = new GuestUserRegisterController(profilemodel.getUser().getUser_id(), profileStage, playerStage, uploadStage, primaryStage, connection, player);
	}

	public void logout(){

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Are you sure?");
		alert.setHeaderText("Do you want to register first?");
		alert.setContentText("Not registering an account means all guest user info will be deleted.");

		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");

		alert.getButtonTypes().setAll(yes, no);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yes){
			UserRegisterController register = new GuestUserRegisterController(profilemodel.getUser().getUser_id(), profileStage, playerStage, uploadStage, primaryStage, connection, player);
		} else {
			UserDAO UD = new UserDAODB(connection);
			UD.deleteUser(profilemodel.getUser().getUser_id());
			playerStage.close();
			uploadStage.close();
			profileStage.close();
			player.endPlayer();
			LoginController login = new LoginController(primaryStage);
		}
	}

	@Override
	public void sayHi() {
		System.out.println("Guest User says Hi");
	}
}
