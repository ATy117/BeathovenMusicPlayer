package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Dashboard;
import model.DashboardModel;
import model.Song;
import model.Track;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DashboardView extends View{

	private Dashboard viewState;
	private DashboardModel model;
	
	public Text usernameHeader;
	public JFXButton songsTab;
	public JFXButton playlistsTab;
	public JFXButton profilesTab;
	public AnchorPane pageArea;
	public Text curSongName;
	public Text curSongArtist;
	public JFXButton logoutButton;
	public JFXButton viewDetailsButton;

	@FXML public AnchorPane songsPane;
	@FXML public AnchorPane playlistsPane;
	@FXML public AnchorPane profilesPane;
	@FXML public AnchorPane createProfilePane;

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
		loadAllViews();
		setCurrentPane(songsPane);
	}

	@Override
	public void Update() {
		viewState = model.getDashboardState();
	}

	public void changeTab (ActionEvent actionEvent){
		if (actionEvent.getSource()==songsTab) {
			setCurrentPane(songsPane);
		}
		else if (actionEvent.getSource() == playlistsTab) {
			setCurrentPane(playlistsPane);
		}
		else if (actionEvent.getSource() == profilesTab) {
			if (!registered) {
				setCurrentPane(createProfilePane);
			}
			else {
				setCurrentPane(profilesPane);
			}
		}

	}


	public void logoutUser(ActionEvent actionEvent) throws IOException {
		Parent dashboard = FXMLLoader.load(getClass().getResource("loginTemplate.fxml"));
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

	public void loadAllViews () throws IOException {

		FXMLLoader loader;

		loader = new FXMLLoader(getClass().getResource("songsTabTemplate.fxml"));
		SongsTabPage songsTab = new SongsTabPage(this);
		loader.setController(songsTab);
		songsPane = loader.load();

		loader = new FXMLLoader(getClass().getResource("playlistsTabTemplate.fxml"));
		PlaylistTabPage playlistsTab = new PlaylistTabPage(this);
		loader.setController(playlistsTab);
		playlistsPane = loader.load();

		loader = new FXMLLoader(getClass().getResource("profilesTabTemplate.fxml"));
		ProfilesTabPage myprofile = new ProfilesTabPage(this);
		loader.setController(myprofile);
		profilesPane = loader.load();

		loader = new FXMLLoader(getClass().getResource("createProfTemplate.fxml"));
		CreateProfilePage newProfilesTab = new CreateProfilePage(this);
		loader.setController(newProfilesTab);
		createProfilePane = loader.load();
	}

	public void setCurrentPane (Pane pane) {
		pageArea.getChildren().clear();
		pageArea.getChildren().add(pane);
	}

	public void setAppDirectory() {
		appdirectory = System.getProperty("user.home") + "/Documents/Beathoven/";
		File appfolder = new File(appdirectory);
		if(!appfolder.exists()) {
			appfolder.mkdir();
		}
	}
}
