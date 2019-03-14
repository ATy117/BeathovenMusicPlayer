package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

	public JFXPopup errorPopup = new JFXPopup();
	public AnchorPane errorAnchor = new AnchorPane();

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
			if(controller.loginRegUser(usernameField.getText(), passwordField.getText()) == false) {
				errorAnchor.getStylesheets().add("view/theme.css");
				errorAnchor.getStyleClass().add("anchorPane-Error");

				Image error = new Image("resources/error.png");
				ImageView errorView = new ImageView(error);
				Text errorMessage = new Text("Create Account First");
				errorMessage.getStyleClass().add("text-input-Error");
				AnchorPane.setTopAnchor(errorMessage, 93.0);
				AnchorPane.setLeftAnchor(errorMessage, 20.0);
				AnchorPane.setTopAnchor(errorView, 30.0);
				AnchorPane.setLeftAnchor(errorView, 27.0);
				errorAnchor.getChildren().add(errorView);
				errorAnchor.getChildren().add(errorMessage);

				errorAnchor.setMinSize(220.0, 150.0);
				errorAnchor.setMaxSize(220.0, 150.0);
				errorPopup.setPopupContent(errorAnchor);
				errorPopup.show(loginAnchor, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);

			}

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
