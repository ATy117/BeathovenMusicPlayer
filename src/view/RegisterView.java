package view;

import com.jfoenix.controls.JFXButton;
import controller.StageManager;
import controller.WelcomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class RegisterView {

	Stage primaryStage;
	WelcomeController controller;

	@FXML public JFXButton backBtn;

	public RegisterView (Stage primaryStage, WelcomeController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPageTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Register an Account");
	}

	public void changePane(javafx.event.ActionEvent actionEvent) {
		if (actionEvent.getSource() == backBtn) {
			controller.changeToWelcome();
		}
	}

	public void uploadProfilePic(ActionEvent actionEvent) {
	}

	public void registerAccount(ActionEvent actionEvent) {
		controller.registerUser("user", "name", "pic");
	}


}
