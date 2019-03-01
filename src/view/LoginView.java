package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginView extends View {

	Stage stage;
	MasterController controller;

	public LoginView(MasterController controller) throws IOException{

		this.controller = controller;
		stage = controller.getStage();


		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
	}

	public void loginUser(ActionEvent actionEvent) {
		// get user / pass from textfield
		controller.loginUser("User", "Pass");
	}

	@Override
	public void Update() {}
}
