package view;

import controller.SceneManager;
import controller.WelcomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterView {

	Stage primaryStage;
	WelcomeController controller;

	public RegisterView (Stage primaryStage, WelcomeController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);

		SceneManager sm = new SceneManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Register an Account");
	}
	


	public void changePane(javafx.event.ActionEvent actionEvent) {
	}

	public void uploadProfilePic(ActionEvent actionEvent) {
	}

	public void registerAccount(ActionEvent actionEvent) {
	}


}
