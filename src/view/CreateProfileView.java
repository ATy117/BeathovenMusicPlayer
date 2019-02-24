package view;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class CreateProfileView {
	public JFXButton createBackButton;
	public JFXButton createProfButton;

	public void changePane(ActionEvent actionEvent) throws IOException {

		if (actionEvent.getSource() == createProfButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardTemplate.fxml"));
			Stage myStage = (Stage) createProfButton.getScene().getWindow();
			createProfButton.setText("Creating...");
			PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
			pause.setOnFinished(e -> myStage.setScene(new Scene(dashboard)));
			pause.play();
		}
		else if (actionEvent.getSource() == createBackButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("mainLoginTemplate.fxml"));
			Stage myStage = (Stage) createBackButton.getScene().getWindow();
			myStage.setScene(new Scene(dashboard));
		}
	}
}
