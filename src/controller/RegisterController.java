package controller;


import dbservice.UserDAOLocal;
import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.User;
import view.RegisterView;

import java.sql.Connection;

public class RegisterController {

	private Stage registerStage;
	private Stage primaryStage;
	private Connection connection;

	public RegisterController(Stage primaryStage, Connection connection) {
		this.primaryStage = primaryStage;
		this.connection = connection;
		registerStage = new Stage();
		RegisterView reg = new RegisterView(registerStage, this);
	}

	public boolean registerUser(String username, String password, String pic) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		registerStage.close();
		primaryStage.close();
		DashboardController controller  = new RegisteredUserController(primaryStage, connection, dude);

		return true;
	}

	public void changeToWelcome() {
		LoginController login = new LoginController(primaryStage);
	}


}
