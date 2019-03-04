package controller;


import dbservice.UserDAOLocal;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model_rework.User;
import view.RegisterView;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.util.List;

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
		DashboardController controller  = new RegisteredDashboardController(primaryStage, connection, dude);
		return true;
	}

	public void changeToWelcome() {
		LoginController login = new LoginController(primaryStage);
	}

	

}
