package view;

import controller.MasterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dashboard;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class DashboardView extends View {

	MasterController controller;
	Dashboard dashboard;
	Stage stage;

	public DashboardView(MasterController controller, Dashboard dashboard) throws IOException {
		this.controller = controller;
		this.dashboard = dashboard;
		// dashbord attach(this)

		stage = controller.getStage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();

		Scene scene = new Scene(root);
		stage.setTitle("Beathoven");
		stage.setScene(scene);
	}

	public void playSong(ActionEvent actionEvent) {
		// get song index
		// controller.playSong(index, d.getUser_ID);
	}

	@Override
	public void Update() {
	}
}

