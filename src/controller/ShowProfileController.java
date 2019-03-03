package controller;

import javafx.stage.Stage;
import model_rework.ProfileModel;
import view.ShowProfileView;

public class ShowProfileController {

	private Stage primaryStage;
	private ProfileModel profilemodel;

	public ShowProfileController(Stage primaryStage, ProfileModel profilemodel) {
		this.primaryStage = primaryStage;
		this.profilemodel = profilemodel;

		ShowProfileView view = new ShowProfileView(primaryStage, profilemodel, this);
		profilemodel.Attach(view);
	}
}
