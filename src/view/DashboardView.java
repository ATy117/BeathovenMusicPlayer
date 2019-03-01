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

	public void logout(ActionEvent actionEvent) {

	}

	public void displayPlaylist(ActionEvent actionEvent) {

	}

	public void displayAllSongs(ActionEvent actionEvent) {

	}

	public void addSong(ActionEvent actionEvent) {

	}

	public void editSong(ActionEvent actionEvent) {

	}

	public void searchSong(ActionEvent actionEvent) {

	}

	public void filterSongs(ActionEvent actionEvent) {

	}

	public void createPlaylist(ActionEvent actionEvent) {

	}

	public void changePane(ActionEvent actionEvent) {

	}

	public void playSong(ActionEvent actionEvent) {
		// get song index
		// controller.playSong(index, d.getUser_ID);
	}

	public void deleteSong(ActionEvent actionEvent) {

	}

	@Override
	public void Update() {
	}
}

