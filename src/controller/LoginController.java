package controller;

import dbservice.UserDAO;
import dbservice.UserDAODB;
import dbservice.dbConnection;
import javafx.stage.Stage;
import model_rework.RegisteredUser;
import view.LoginView;

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
		DashboardController controller  = new GuestUserDashboardController(primaryStage, connection);
		return true;
	}

	public boolean loginRegUser(String username, String password) {

		UserDAO worker = new UserDAODB(connection);

		RegisteredUser dude =(RegisteredUser) worker.getUser(username, password);
		if (dude != null) {
			DashboardController controller = new RegisteredUserDashboardController(primaryStage, connection, dude);
		} else {
			System.out.println("Create Account First");
			return false;
		}
		return true;
	}


	public void changeToRegister() {
		UserRegisterController register = new NewUserRegisterController(primaryStage, connection);
	}



}
