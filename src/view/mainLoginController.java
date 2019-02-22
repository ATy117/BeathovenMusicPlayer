package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainLoginController {
	public JFXButton loginButton;
	public JFXButton accountRegisterButton;
	public JFXButton guestLoginButton;

	public void changeLoginPane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == loginButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardView.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			myStage.setScene(new Scene(dashboard));
		}
		else if (actionEvent.getSource() == guestLoginButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardView.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			myStage.setScene(new Scene(dashboard));
		}
		else if (actionEvent.getSource() == accountRegisterButton) {
			Parent register = FXMLLoader.load(getClass().getResource("registerView.fxml"));
			Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
			myStage.setScene(new Scene(register));
		}
	}
}
