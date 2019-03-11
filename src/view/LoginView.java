package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.StageManager;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView  {

	Stage primaryStage;
	LoginController controller;
	StageManager sm;

	@FXML public JFXButton loginBtn;
	@FXML public JFXButton loginGuestBtn;
	@FXML public JFXButton registerBtn;
	@FXML public JFXTextField usernameField;
	@FXML public JFXPasswordField passwordField;
	@FXML public AnchorPane loginAnchor;

	public LoginView(Stage primaryStage, LoginController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);

		sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Welcome to Beathoven");

		loginAnchor.getStylesheets().add("view/theme.css");
		usernameField.getStyleClass().add("jfx-text-field-LogIn");
		passwordField.getStyleClass().add("jfx-text-field-LogIn");
	}

	public void loginUser(javafx.event.ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == loginBtn) {
			controller.loginRegUser(usernameField.getText(), passwordField.getText());
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
