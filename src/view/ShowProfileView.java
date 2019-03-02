package view;

import controller.ShowProfileController;
import controller.WelcomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowProfileView extends View{


	Stage primaryStage;
	ShowProfileController controller;

	public ShowProfileView (Stage primaryStage, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);
		Parent root = null;
		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);

		primaryStage.setTitle("Your Profile");
		primaryStage.setScene(scene);
		primaryStage.show();
	}




	@Override
	public void Update(){}

	public void changePane(ActionEvent actionEvent) {
	}

	public void editUserDetails(ActionEvent actionEvent) {
	}
}
