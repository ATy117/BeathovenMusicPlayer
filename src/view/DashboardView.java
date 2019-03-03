package view;

import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model_rework.*;

public class DashboardView extends View {

	private DashboardModel model;
	private Dashboard viewState;

	public DashboardView (Stage stage, DashboardModel model, DashboardController controller) {

		this.controller = controller;
		this.model = model;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(stage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven");
	}

	@Override
	public void Update() {

	}

	public void changePane(ActionEvent actionEvent) {
	}

	public void createPlaylist(ActionEvent actionEvent) {
		controller.sayHi();
	}
}

