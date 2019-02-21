package view;

import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class dashboardController {

	public JFXScrollPane mainScrollPane;
	public Text usernameHeader;

	public void switchSongsPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);

	}

	public void switchPlaylistsPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistsTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);

	}

	public void switchFavoritesPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("favoritesTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);
	}

	public void switchProfilePane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);

	}

	public void clearPanes() {
		mainScrollPane.getChildren().clear();
	}



}
