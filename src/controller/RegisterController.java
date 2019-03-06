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

public class RegisterController {

	private Stage primaryStage;
	private Connection connection;

	public RegisterController(Stage primaryStage, Connection connection) {
		this.primaryStage = primaryStage;
		this.connection = connection;
		RegisterView reg = new RegisterView(primaryStage, this);
	}

	public boolean registerUser(String username, String password, String firstname, String lastname, File avatar) {
		UserDAO worker = new UserDAODB(connection);
		if (worker.checkUsername(username)){
			System.out.println("Username already exists");
			return false;
		} else {
			RegisteredUserBuilder RUB = new RegisteredUserBuilder();
			RegisteredUser RU = RUB
					.withAvatar(avatar)
					.withFirstName(firstname)
					.withLastName(lastname)
					.withUsername(username)
					.withPassword(password)
					.build();
			worker.addUser(RU);
			User dude = worker.getUser(username, password);
			DashboardController controller = new RegisteredUserController(primaryStage, connection, dude);
			return true;
		}
	}

	public void changeToWelcome() {
		LoginController login = new LoginController(primaryStage);
	}


}
