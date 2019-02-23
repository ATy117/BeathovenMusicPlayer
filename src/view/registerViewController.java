package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class registerViewController {

	public JFXButton registerConfirmButton;
	public JFXButton registerBackButton;
	public ImageView logoIV;


	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == registerConfirmButton) {
			Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardView.fxml"));
			Stage myStage = (Stage) registerConfirmButton.getScene().getWindow();
			myStage.setScene(new Scene(dashboard));
		}
		else if (actionEvent.getSource() == registerBackButton ){
			Parent login = FXMLLoader.load(getClass().getResource("mainLoginView.fxml"));
			Stage myStage = (Stage) registerBackButton.getScene().getWindow();
			myStage.setScene(new Scene(login));
		}
	}
}
