package view;

import controller.WelcomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SongPlayerView{

	Stage primaryStage;
	WelcomeController controller;

	public SongPlayerView (Stage primaryStage, WelcomeController controller) throws IOException{

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPageTemplate.fxml"));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public void playPause(ActionEvent actionEvent) {
	}

	public void nextSong(ActionEvent actionEvent) {
	}

	public void prevSong(ActionEvent actionEvent) {
	}

	public void shuffle(ActionEvent actionEvent) {
	}

	public void repeat(ActionEvent actionEvent) {
	}

	public void goToDuration(MouseEvent mouseEvent) {
	}
}
