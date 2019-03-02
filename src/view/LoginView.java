package view;

import com.jfoenix.controls.JFXButton;
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

	public LoginView(Stage primaryStage, WelcomeController controller) throws IOException{

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.show();
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

	}
}
