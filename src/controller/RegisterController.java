package controller;


import dbservice.UserDAOLocal;
import javafx.stage.Stage;
import model_rework.User;
import view.RegisterView;

import java.sql.Connection;

public class RegisterController {

	private Stage primaryStage;
	private Connection connection;

	public RegisterController(Stage primaryStage, Connection connection) {
		this.primaryStage = primaryStage;
		this.connection = connection;
		RegisterView reg = new RegisterView(primaryStage, this);
	}

	public boolean registerUser(String username, String password, String pic) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		DashboardController controller  = new RegisteredUserController(primaryStage, connection, dude);
		return true;
	}

	public void changeToWelcome() {
		LoginController login = new LoginController(primaryStage);
	}


}
