package view;

import com.jfoenix.controls.JFXButton;
import controller.MasterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginView extends View {

	@FXML public JFXButton loginBtn;

	public LoginView(MasterController controller, Stage stage) throws IOException{

		this.controller = controller;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
	}



	public void changePane(ActionEvent actionEvent) {

	}

	@Override
	public void Update() {}


	public void loginUser(javafx.event.ActionEvent actionEvent)  throws IOException{
		controller.loginUser("User", "Pass");
	}
}
