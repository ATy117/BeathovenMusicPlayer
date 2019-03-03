package view;

import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ShowProfileView extends View{


	Stage primaryStage;
	ShowProfileController controller;

	public ShowProfileView (Stage primaryStage, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
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
