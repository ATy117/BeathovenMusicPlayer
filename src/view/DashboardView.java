package view;

import controller.DashboardController;
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
		Parent root = null;

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setTitle("Beathoven");
		stage.setScene(scene);
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

