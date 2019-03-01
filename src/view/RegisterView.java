package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class RegisterView extends View{
	Stage stage;
	MasterController controller;

	public RegisterView(MasterController controller) throws IOException {

		this.controller = controller;
		stage = controller.getStage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("Register Account");
		stage.setScene(scene);
	}

	public void RegisterAccount(ActionEvent actionEvent) {
		// get user name / pass, pic
		File test = new File("test");
		controller.registerAccount("user", "pass", test);
	}

	@Override
	public void Update() {}
}
