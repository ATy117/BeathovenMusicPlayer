package view;

import com.jfoenix.controls.JFXButton;
import controller.SceneManager;
import controller.WelcomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginView  {

	Stage primaryStage;
	WelcomeController controller;

	@FXML public JFXButton loginBtn;
	@FXML public JFXButton loginGuestBtn;
	@FXML public JFXButton registerBtn;

	public LoginView(Stage primaryStage, WelcomeController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);

		SceneManager sm = new SceneManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Welcome to Beathoven");
	}



	public void changePane(ActionEvent actionEvent) {

	}

	public void loginUser(javafx.event.ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == loginBtn) {
			controller.loginRegUser("Name", "Pass");
		}
		else if (actionEvent.getSource() == loginGuestBtn)
			controller.loginGuestUser("Name", "Pass");
	}

	public void changePane(javafx.event.ActionEvent actionEvent) {
		if (actionEvent.getSource() == registerBtn ) {
			controller.changeToRegister();
		}
	}
}
