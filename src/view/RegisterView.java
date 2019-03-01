package view;

import controller.MasterController;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;

public class RegisterView extends View{
	Stage stage;
	MasterController controller;

	public RegisterView(MasterController controller) {
		this.controller = controller;
		stage = controller.getStage();
		// loads FXML
		// stage.setScene(fxml loader);
	}

	public void RegisterAccount(ActionEvent actionEvent) {
		// get user name / pass, pic
		File test = new File("test");
		controller.registerAccount("user", "pass", test);
	}

	@Override
	public void Update() {}
}
