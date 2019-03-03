package view;

import com.jfoenix.controls.JFXButton;
import controller.StageManager;
import controller.WelcomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginView  {

	Stage primaryStage;
	WelcomeController controller;
	StageManager sm;

	@FXML public JFXButton loginBtn;
	@FXML public JFXButton loginGuestBtn;
	@FXML public JFXButton registerBtn;

	public LoginView(Stage primaryStage, WelcomeController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);

		sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Welcome to Beathoven");
	}

	public void loginUser(javafx.event.ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == loginBtn) {
			controller.loginRegUser("Name", "Pass");
		}
		else if (actionEvent.getSource() == loginGuestBtn)
			controller.loginGuestUser();
	}

	public void changePane(javafx.event.ActionEvent actionEvent) {
		if (actionEvent.getSource() == registerBtn ) {
			controller.changeToRegister();
		}
	}
}
