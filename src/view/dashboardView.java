package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class DashboardView {
	
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton favoritesTab;
	public JFXButton profilesTab;
	public AnchorPane pageArea;
	public JFXButton logoutButton;
	public Text curSongName;
	public Text curSongArtist;


	public void initialize() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabTemplate.fxml"));
		SongsTabView songsTab = new SongsTabView(this);
		loader.setController(songsTab);
		pageArea.getChildren().add(loader.load());
		usernameHeader.setText("gab");
		usernameHeader.setText("gab");
		curSongName.setText("No song playing");
		curSongArtist.setText("");
	}

	public void changeTab (ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource()==songsTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabTemplate.fxml"));
			SongsTabView songsTab = new SongsTabView(this);
			loader.setController(songsTab);
			pageArea.getChildren().add(loader.load());

		}
		else if (actionEvent.getSource() == playlistsTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("playlistsTabTemplate.fxml"));
			PlaylistsTabView playlistsTab = new PlaylistsTabView(this);
			loader.setController(playlistsTab);
			pageArea.getChildren().add(loader.load());

		}
		else if (actionEvent.getSource() == favoritesTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("favoritesTabTemplate.fxml"));
			FavoritesTabView favoritesTab = new FavoritesTabView(this);
			loader.setController(favoritesTab);
			pageArea.getChildren().add(loader.load());
		}
		else if (actionEvent.getSource() == profilesTab) {
			pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabTemplate.fxml"));
			ProfilesTabView profilesTab = new ProfilesTabView(this);
			loader.setController(profilesTab);
			pageArea.getChildren().add(loader.load());
		}
	}


	public void logoutUser(ActionEvent actionEvent) throws IOException {
		Parent dashboard = FXMLLoader.load(getClass().getResource("mainLoginTemplate.fxml"));
		Stage myStage = (Stage) logoutButton.getScene().getWindow();
		myStage.setScene(new Scene(dashboard));
	}


}
