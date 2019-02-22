package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class dashboardController {

	public JFXScrollPane mainScrollPane;
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton favoritesTab;
	public JFXButton profilesTab;

	public void changeTab (ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource()==songsTab) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabView.fxml"));
			mainScrollPane.getChildren().setAll((AnchorPane) loader.load());
		}
		else if (actionEvent.getSource() == playlistsTab) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistsTabView.fxml"));
			mainScrollPane.getChildren().setAll((AnchorPane) loader.load());
		}
		else if (actionEvent.getSource() == favoritesTab) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("favoritesTabView.fxml"));
			mainScrollPane.getChildren().setAll((AnchorPane) loader.load());
		}
		else if (actionEvent.getSource() == profilesTab) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabView.fxml"));
			mainScrollPane.getChildren().setAll((AnchorPane) loader.load());
		}
	}


}
