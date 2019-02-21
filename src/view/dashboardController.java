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
	private songsTabController songsTabControl;
	private favoritesTabController favoritesTabControl;
	private playlistsTabController playlistsTabControl;
	private profilesTabController profilesTabControl;


	public void switchSongsPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(songsTabController.class.getResource("songsTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);
		songsTabControl = loader.getController();
	}

	public void switchPlaylistsPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(songsTabController.class.getResource("playlistsTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);
		playlistsTabControl = loader.getController();
	}

	public void switchFavoritesPane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(songsTabController.class.getResource("favoritesTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);
		favoritesTabControl = loader.getController();
	}

	public void switchProfilePane(ActionEvent actionEvent) throws IOException {
		clearPanes();
		FXMLLoader loader = new FXMLLoader(songsTabController.class.getResource("profilesTabView.fxml"));
		AnchorPane page = loader.load();
		mainScrollPane.getChildren().add(page);
		profilesTabControl = loader.getController();
	}

	public void clearPanes() {
		mainScrollPane.getChildren().clear();
	}



}
