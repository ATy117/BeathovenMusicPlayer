package view;

import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model_rework.ProfileModel;

public class ShowProfileView extends View{

	private Stage primaryStage;
	private ShowProfileController controller;
	private ProfileModel profilemodel;

	public ShowProfileView (Stage primaryStage, ProfileModel profilemodel, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;
		this.profilemodel = profilemodel;

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
