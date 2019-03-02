package controller;

import dbservice.UserDAOLocal;
import javafx.stage.Stage;
import model_rework.User;
import view.LoginView;
import view.RegisterView;

import java.io.IOException;

public class WelcomeController {

	Stage primaryStage;

	public WelcomeController(Stage primaryStage) throws IOException  {
		this.primaryStage = primaryStage;
		LoginView login = new LoginView(primaryStage, this);
	}

	public boolean loginGuestUser(String username, String password) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		DashboardController controller  = new GuestUserController(primaryStage, dude);
		return true;
	}

	public boolean loginRegUser(String username, String password) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		DashboardController controller  = new RegisteredUserController(primaryStage, dude);
		return true;
	}

	public void changeToRegister() {
		
	}

}
