package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Song;
import model.Track;
import org.controlsfx.control.PopOver;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DashboardView extends View{
	
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton profilesTab;
	public AnchorPane pageArea;
	public Text curSongName;
	public Text curSongArtist;
	public JFXButton logoutButton;
	public JFXButton viewDetailsButton;

	public String appdirectory;

	Boolean registered;
	List<Track> songlist = new ArrayList<>();

	public void initialize() throws IOException{

		registered=false;

		for (int i=0; i<30; i++) {
			Track music = new Song();
			music.setName("song" + i);
			songlist.add(music);
		}



		setAppDirectory();
		setCurrentUser();
		setPlayingSong(null);


		FXMLLoader loader = new FXMLLoader(getClass().getResource("songsTabTemplate.fxml"));
		SongsTabView songsTab = new SongsTabView(this);
		loader.setController(songsTab);
		pageArea.getChildren().add(loader.load());
	}

	@Override
	public void Update() {}

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
		else if (actionEvent.getSource() == profilesTab) {
			if (!registered) {
				pageArea.getChildren().clear();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("createProfTemplate.fxml"));
				CreateProfileView newProfilesTab = new CreateProfileView(this);
				loader.setController(newProfilesTab);
				pageArea.getChildren().add(loader.load());

			}
			else {
				pageArea.getChildren().clear();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabTemplate.fxml"));
				ProfilesTabView myprofile = new ProfilesTabView(this);
				loader.setController(myprofile);
				pageArea.getChildren().add(loader.load());
			}
		}



	}


	public void logoutUser(ActionEvent actionEvent) throws IOException {
		Parent dashboard = FXMLLoader.load(getClass().getResource("mainLoginTemplate.fxml"));
		Stage myStage = (Stage) logoutButton.getScene().getWindow();
		myStage.setScene(new Scene(dashboard));
	}

	public void sayHi () {
		System.out.println("hi");
	}



	public void setCurrentUser() {
		usernameHeader.setText("gab");
	}

	public void setPlayingSong(Song current) {

		if (current!= null) {
			curSongName.setText(current.getName());
			curSongArtist.setText("artist");
		}
		else {
			curSongName.setText("No Song Playing");
			curSongArtist.setText("");
		}
	}

	public void displaySongDetails(ActionEvent actionEvent) {
		AnchorPane details = new AnchorPane();
		details.setMinWidth(150);
		details.setMinHeight(150);


		PopOver songDetails = new PopOver();

		songDetails.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
		songDetails.setContentNode(details);
		songDetails.setAutoFix(true);
		songDetails.setAutoHide(true);
		songDetails.setHideOnEscape(true);
		songDetails.setDetachable(false);
		songDetails.show((Button)actionEvent.getSource());
	}

	public void setAppDirectory() {
		appdirectory = System.getProperty("user.home") + "/Documents/Beathoven/";
		File appfolder = new File(appdirectory);
		if(!appfolder.exists()) {
			appfolder.mkdir();
		}
	}
}
