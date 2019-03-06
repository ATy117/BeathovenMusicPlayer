package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.*;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.WHITE;

public class DashboardView extends View {

	private SongPlayerModel songplayermodel;
	private LibraryModel librarymodel;
	private ProfileModel profilemodel;

	@FXML public JFXButton myProfileBtn;
	@FXML public JFXButton uploadAddSongsBtn;
	@FXML public JFXListView populateSongsList;
	@FXML public VBox newPLaylistVbox;
	@FXML public VBox albumsVbox;
	@FXML public JFXComboBox filterCombo;


	public DashboardView (Stage stage, SongPlayerModel songplayermodel, LibraryModel librarymodel, ProfileModel profilemodel, DashboardController controller) {

		super(controller);
		this.songplayermodel = songplayermodel;
		this.librarymodel = librarymodel;
		this.profilemodel = profilemodel;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(stage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven");

		populateSong((ArrayList<Song>)librarymodel.getSongList());

		init();
	}

	@Override
	public void Update() {
		populateSong((ArrayList<Song>)librarymodel.getSongList());
		populateAlbum((ArrayList<Album>)librarymodel.getAlbumList());
	}

	public void changePane(ActionEvent actionEvent) {
		if (actionEvent.getSource() == myProfileBtn) {
			controller.viewProfile();
		}
		else if (actionEvent.getSource() == uploadAddSongsBtn) {
			controller.uploadSong();
		}
	}

	public void createPlaylist(ActionEvent actionEvent) {
		JFXTextField playlistField = new JFXTextField();
		playlistField.setPromptText("Playlist Name");
		newPLaylistVbox.getChildren().add(playlistField);
		playlistField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					System.out.println(playlistField.getText());
					newPLaylistVbox.getChildren().remove(playlistField);
					createPlaylistButton(playlistField.getText());
				}
			}
		});

		controller.sayHi();
	}

	public void createPlaylistButton(String playlistName)
	{
		newPLaylistVbox.getStylesheets().add("view/theme.css");
		JFXButton playlistButton = new JFXButton(playlistName);
		newPLaylistVbox.getChildren().add(playlistButton);
		System.out.println("Button Created");

	}

	public void populateAlbum(ArrayList<Album> albumList)
	{
		albumsVbox.getStylesheets().add("view/theme.css");
		albumsVbox.getChildren().clear();
		for(Album a: albumList){
			JFXButton newAlbumBtn = new JFXButton(a.getName());
			albumsVbox.getChildren().add(newAlbumBtn);
		}

	}

	public void albumCreated(String name)
	{
		albumsVbox.getStylesheets().add("view/theme.css");
		JFXButton newAlbum = new JFXButton(name);
		albumsVbox.getChildren().add(newAlbum);
	}

	public void showMusicPlayer() {
		System.out.println("Show Music Player");
	}

	public void populateSong(ArrayList<Song> songlist){
		populateSongsList.getItems().clear();
		for(Song s : songlist)
		{
			HBox hbox = new HBox();
			Text songName = new Text(s.getSong_name());
			Text space = new Text("        ");
			Text space2 = new Text("        ");
			Text songArtist = new Text(s.getArtist_name());
			JFXButton playButton = new JFXButton();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			playView.setFitWidth(15);
			playView.setFitHeight(20);
			playButton.setGraphic(playView);

			songName.setFont(Font.font("Poppins", 14));
			songArtist.setFont(Font.font("Poppins", 14));

			hbox.getChildren().add(playButton);
			hbox.getChildren().add(space2);
			hbox.getChildren().add(songName);
			hbox.getChildren().add(space);
			hbox.getChildren().add(songArtist);
			populateSongsList.getItems().add(hbox);
		}

	}

	public void init()
	{
		filterCombo.getItems().add("Genre");
		filterCombo.getItems().add("Album");
		filterCombo.getItems().add("Year");

	}


}

