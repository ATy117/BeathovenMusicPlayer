package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {

	public AnchorPane registerAnchor;
	public AnchorPane welcomeAnchor;
	public JFXButton registerBack;
	public AnchorPane createProfileAnchor;
	public JFXButton guestLoginButton;

	public void changeToRegister(ActionEvent actionEvent) {
		welcomeAnchor.setVisible(false);
		registerAnchor.setVisible(true);
	}

	public void changeToWelcome(ActionEvent actionEvent) {
		registerAnchor.setVisible(false);
		welcomeAnchor.setVisible(true);
	}

	public void changeToDashboard(ActionEvent actionEvent) throws Exception {
		Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardView.fxml"));
		Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
		myStage.setScene(new Scene(dashboard, 1000, 600));
	}




}
