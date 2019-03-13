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

	public GuestUserRegisterController(Stage profileStage, Stage playerStage, Stage uploadStage, Stage primaryStage, Connection connection) {
		this.primaryStage = primaryStage;
		this.playerStage = playerStage;
		this.uploadStage = uploadStage;
		this.profileStage = profileStage;
		this.connection = connection;
		RegisterView reg = new RegisterView(profileStage, this);
	}

	public boolean registerUser(String username, String password, String firstname, String lastname, File avatar) {
		return true;
	}

	@Override
	public void backPane() {
		profileStage.close();
	}

}
