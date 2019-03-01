package view;

import controller.MasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RegisterView extends View{

	public RegisterView(MasterController controller, Stage stage) throws IOException {

		this.controller = controller;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Register Account");
		stage.setScene(scene);
	}
	


	public void changePane(javafx.event.ActionEvent actionEvent) {
	}

	public void uploadProfilePic(ActionEvent actionEvent) {
	}

	public void registerAccount(ActionEvent actionEvent) {
	}


	@Override
	public void Update() {}
}
