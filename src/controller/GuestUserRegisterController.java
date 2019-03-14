package controller;


import dbservice.UserDAO;
import dbservice.UserDAODB;
import dbservice.UserDAOLocal;
import javafx.stage.Stage;
import model_rework.RegisteredUser;
import model_rework.RegisteredUserBuilder;
import model_rework.User;
import view.RegisterView;

import java.io.File;
import java.sql.Connection;

public class GuestUserRegisterController extends UserRegisterController{

	private Stage playerStage;
	private Stage uploadStage;
	private Stage profileStage;
	private SongPlayerController player;
	private int user_id;

	public GuestUserRegisterController(int n, Stage profileStage, Stage playerStage, Stage uploadStage, Stage primaryStage, Connection connection, SongPlayerController player) {
		this.primaryStage = primaryStage;
		this.playerStage = playerStage;
		this.uploadStage = uploadStage;
		this.profileStage = profileStage;
		this.connection = connection;
		this.player = player;
		user_id = n;
		RegisterView reg = new RegisterView(profileStage, this);
	}

	public boolean registerUser(String username, String password, String firstname, String lastname, File avatar) {
		UserDAO UD = new UserDAODB(connection);
		RegisteredUserBuilder RUB = new RegisteredUserBuilder();
		RegisteredUser RU = RUB
				.withUserID(user_id)
				.withAvatar(avatar)
				.withFirstName(firstname)
				.withLastName(lastname)
				.withUsername(username)
				.withPassword(password)
				.build();
		UD.updateUser(RU);

		uploadStage.close();
		profileStage.close();
		playerStage.close();
		player.endPlayer();
		DashboardController controller = new RegisteredUserDashboardController(primaryStage, connection, RU);
		return true;
	}

	@Override
	public void backPane() {
		profileStage.close();
	}

}
