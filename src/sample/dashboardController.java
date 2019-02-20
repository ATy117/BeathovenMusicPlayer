package sample;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class dashboardController {

	public AnchorPane songsPane;
	public AnchorPane playlistsPane;
	public AnchorPane favoritesPane;

	public void switchSongsPane(ActionEvent actionEvent) {
		playlistsPane.setVisible(false);
		favoritesPane.setVisible(false);
		songsPane.setVisible(true);
	}

	public void switchPlaylistsPane(ActionEvent actionEvent) {
		songsPane.setVisible(false);
		favoritesPane.setVisible(false);
		playlistsPane.setVisible(true);
	}

	public void switchFavoritesPane(ActionEvent actionEvent) {
		playlistsPane.setVisible(false);
		songsPane.setVisible(false);
		favoritesPane.setVisible(true);
	}


}
