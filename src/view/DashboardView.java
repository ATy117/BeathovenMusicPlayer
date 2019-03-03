package view;

import controller.DashboardController;
import controller.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model_rework.*;

import java.io.IOException;

public class DashboardView extends View {

	private DashboardModel model;
	private Dashboard viewState;

	public DashboardView (Stage stage, DashboardModel model, DashboardController controller) {

		this.controller = controller;
		this.model = model;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		SceneManager sm = new SceneManager(stage);
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

