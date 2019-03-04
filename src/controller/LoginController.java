package controller;

import dbservice.UserDAOLocal;
import dbservice.dbConnection;
import javafx.stage.Stage;
import model_rework.User;
import view.LoginView;
import view.RegisterView;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {

	private Stage primaryStage;
	private Connection connection;

	public LoginController(Stage primaryStage)   {

		dbConnection connector = new dbConnection();
		connection = connector.getConnection();
		this.primaryStage = primaryStage;
		LoginView login = new LoginView(primaryStage, this);
	}

	public boolean loginGuestUser() {
		DashboardController controller  = new GuestUserController(primaryStage, connection);
		return true;
	}

	public boolean loginRegUser(String username, String password) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		DashboardController controller  = new RegisteredUserController(primaryStage, connection, dude);
		return true;
	}


	public void changeToRegister() {
		RegisterController register = new RegisterController(primaryStage, connection);
	}



}
