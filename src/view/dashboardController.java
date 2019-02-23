package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController {
	
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton favoritesTab;
	public JFXButton profilesTab;
	public AnchorPane pageArea;
	public JFXButton logoutButton;

	public void initialize() {
		usernameHeader.setText("gab");
	}

	public void changeTab (ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource()==songsTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabView.fxml"));
			pageArea.getChildren().add(loader.load());

		}
		else if (actionEvent.getSource() == playlistsTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistsTabView.fxml"));
			pageArea.getChildren().add(loader.load());

		}
		else if (actionEvent.getSource() == favoritesTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("favoritesTabView.fxml"));
			pageArea.getChildren().add(loader.load());
		}
		else if (actionEvent.getSource() == profilesTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabView.fxml"));
			pageArea.getChildren().add(loader.load());
		}
	}


	public void logoutUser(ActionEvent actionEvent) throws IOException {
		Parent dashboard = FXMLLoader.load(getClass().getResource("mainLoginView.fxml"));
		Stage myStage = (Stage) logoutButton.getScene().getWindow();
		myStage.setScene(new Scene(dashboard));
	}

	public void changeUsername (String s) {
		usernameHeader.setText(s);
	}
}
