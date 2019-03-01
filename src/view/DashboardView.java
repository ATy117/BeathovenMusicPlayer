package view;

import controller.MasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model_rework.Dashboard;

import java.io.IOException;

public class DashboardView extends View {

	Dashboard dashboard;

	public DashboardView(MasterController controller, Dashboard dashboard, Stage stage) throws IOException {
		this.controller = controller;
		this.dashboard = dashboard;
		// dashbord attach(this)

		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();

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
	}
}

