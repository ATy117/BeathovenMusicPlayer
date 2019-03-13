package controller;

import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;

public abstract class UserRegisterController {

	protected Stage primaryStage;
	protected Connection connection;

	public abstract boolean registerUser(String username, String password, String firstname, String lastname, File file);
}
