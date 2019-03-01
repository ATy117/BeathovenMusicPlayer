package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
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

	public void editUserDetails(ActionEvent actionEvent) {

	}

	public void changePane(ActionEvent actionEvent) {

	}



	@Override
	public void Update(){}
}
