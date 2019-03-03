package controller;

import javafx.stage.Stage;
import view.ShowProfileView;

public class ShowProfileController {

	Stage primaryStage;

	public ShowProfileController(Stage primaryStage) {
		this.primaryStage = primaryStage;
		ShowProfileView view = new ShowProfileView(primaryStage, this);
	}
}
