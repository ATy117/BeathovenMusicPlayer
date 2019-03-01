package view;

import controller.MasterController;
import javafx.stage.Stage;
import model.Dashboard;

import java.awt.event.ActionEvent;

public class DashboardView extends View {

	MasterController controller;
	Dashboard dashboard;
	Stage stage;

	public DashboardView(MasterController controller, Dashboard dashboard) {
		this.controller = controller;
		this.dashboard = dashboard;
		// loads FXML
		// dashbord attach(this)
		stage = controller.getStage();
		// stage  setScene(FXML.load());
	}

	public void playSong(ActionEvent actionEvent) {
		// get song index
		// controller.playSong(index, d.getUser_ID);
	}

	@Override
	public void Update() {
	}
}

