package view;

import controller.MasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowProfileView extends View{



	public ShowProfileView(MasterController controller, Stage stage) throws IOException {

		this.controller = controller;
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		stage.setTitle("My Profile");
		stage.setScene(scene);
	}




	@Override
	public void Update(){}

	public void changePane(ActionEvent actionEvent) {
	}

	public void editUserDetails(ActionEvent actionEvent) {
	}
}
