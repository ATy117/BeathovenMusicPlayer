package view;

import controller.SceneManager;
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

		SceneManager sm = new SceneManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("My Profile");
	}




	@Override
	public void Update(){}

	public void changePane(ActionEvent actionEvent) {
	}

	public void editUserDetails(ActionEvent actionEvent) {
	}
}
