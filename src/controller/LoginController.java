package controller;

import dbservice.UserDAO;
import dbservice.UserDAODB;
import dbservice.UserDAOLocal;
import dbservice.dbConnection;
import javafx.stage.Stage;
import model_rework.RegisteredUser;
import model_rework.RegisteredUserBuilder;
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

		UserDAO worker = new UserDAODB(connection);

		RegisteredUser dude =(RegisteredUser) worker.getUser(username, password);
		if (dude != null) {
			DashboardController controller = new RegisteredUserController(primaryStage, connection, dude);
		} else {
			System.out.println("Create Account First");
		}
		return true;
	}


	public void changeToRegister() {
		RegisterController register = new RegisterController(primaryStage, connection);
	}



}
