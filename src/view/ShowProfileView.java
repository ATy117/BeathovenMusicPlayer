package view;

import com.jfoenix.controls.JFXButton;
import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowProfileView extends View{


	Stage primaryStage;
	ShowProfileController controller;

	@FXML public JFXButton backBtn;
	@FXML public JFXButton logoutBtn;
	@FXML public JFXButton editBtn;

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

	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == backBtn)
			controller.changeToDashboard();
		else if (actionEvent.getSource() == logoutBtn)
			controller.changeToLogin();

	}

	public void editUserDetails(ActionEvent actionEvent) {
		controller.editUserDetails();
	}
}
