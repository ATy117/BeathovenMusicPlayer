package controller;

import dbservice.UserDAOLocal;
import javafx.stage.Stage;
import model_rework.User;
import view.RegisterView;

public class RegisterController {

	public Stage primaryStage;

	public RegisterController(Stage primaryStage) {
		this.primaryStage = primaryStage;
		RegisterView reg = new RegisterView(primaryStage, this);
	}

	public boolean registerUser(String username, String password, String pic) {
		UserDAOLocal worker = new UserDAOLocal();
		User dude = worker.getUser(username, password);
		DashboardController controller  = new RegisteredUserController(primaryStage, dude);
		return true;
	}

	public void changeToWelcome() {
		LoginController login = new LoginController(primaryStage);
	}


}
