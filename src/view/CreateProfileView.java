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
	public JFXButton createProfButton;
	DashboardView parent;

	public CreateProfileView (DashboardView parent) {
		this.parent = parent;
	}

	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == createProfButton) {
			parent.pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabTemplate.fxml"));
			ProfilesTabView myprofile = new ProfilesTabView(parent);
			loader.setController(myprofile);
			parent.pageArea.getChildren().add(loader.load());
		}
	}
}
