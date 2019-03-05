package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
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
	@FXML public VBox playlistVbox;
	@FXML public JFXListView populateSongsList;


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
	}

	@Override
	public void Update() {
		populateSong((ArrayList<Song>)librarymodel.getSongList());
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
		JFXTextField playlistField = new JFXTextField("PlaylistName");
		//playlistField.getFocusColor(Color.getColor("#8ba0a9"));
		playlistVbox.getChildren().add(playlistField);

		controller.sayHi();
	}

	public void showMusicPlayer() {
		System.out.println("Show Music Player");
	}

	public void populateSong(ArrayList<Song> songlist){
		for(Song s : songlist)
		{
			HBox hbox = new HBox();
			Text text = new Text(s.getSong_name());
			text.setFont(Font.font("Poppins", 14));
			hbox.getChildren().add(text);
			populateSongsList.getItems().add(hbox);
		}

	}


}

