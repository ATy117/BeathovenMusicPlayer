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

public class mainLoginView {
	public JFXButton loginButton;
	public JFXButton accountRegisterButton;
	public JFXButton guestLoginButton;

	public void changeLoginPane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == loginButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("createProfTemplate.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			loginButton.setText("Logging in...");
			PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
			pause.setOnFinished(e -> myStage.setScene(new Scene(dashboard)));
			pause.play();
		}
		else if (actionEvent.getSource() == guestLoginButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardTemplate.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			guestLoginButton.setText("Logging in...");
			PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
			pause.setOnFinished(e -> myStage.setScene(new Scene(dashboard)));
			pause.play();
		}
		else if (actionEvent.getSource() == accountRegisterButton) {
			Parent register = FXMLLoader.load(getClass().getResource("registerTemplate.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			myStage.setScene(new Scene(register));
		}
	}
}
