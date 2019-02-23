package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class dashboardController {
	
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton favoritesTab;
	public JFXButton profilesTab;
	public AnchorPane pageArea;

	public void initialize() {

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


}
