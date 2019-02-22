package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class startController {


	public JFXButton loginButton;
	public JFXButton accountRegisterButton;
	public JFXButton guestLoginButton;
	public JFXScrollPane loginScrollPane;
	public JFXButton registerBack;
	public JFXButton registerConfirmButton;

	public void initialize() {
		System.out.println("youre in login");
	}

	public void changeToRegister(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerView.fxml"));
		AnchorPane page = loader.load();
		loginScrollPane.getChildren().add(page);
		loader.setController(this);

	}

	public void changeToWelcome(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginView.fxml"));
		AnchorPane page = loader.load();
		loginScrollPane.getChildren().add(page);
		loader.setController(this);
	}

	public void changeToDashboard(ActionEvent actionEvent) throws Exception {
		Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardView.fxml"));
		Stage myStage = (Stage) guestLoginButton.getScene().getWindow();
		myStage.setScene(new Scene(dashboard));
	}

	private void clearPanes() {
		loginScrollPane.getChildren().clear();
	}




}
