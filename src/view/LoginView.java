package view;

import controller.MasterController;
import javafx.stage.Stage;

public class LoginView extends View {

	Stage stage;
	MasterController controller;

	LoginView(MasterController controller) {
		this.controller = controller;
		stage = controller.getStage();
	}

	public void loginUser() {
		// get user / pass from textfield
		controller.loginUser("User", "Pass");
	}

	@Override
	public void Update() {}
}
