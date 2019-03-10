package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.Playlist;
import model_rework.ProfileModel;
import model_rework.Song;

import java.io.IOException;
import java.util.ArrayList;

public class ShowProfileView extends View{

	private Stage primaryStage;
	private ShowProfileController controller;
	private ProfileModel profilemodel;

	@FXML public JFXButton backBtn;
	@FXML public JFXButton logoutBtn;
	@FXML public JFXButton editBtn;
	@FXML public JFXTextField firstNameField;
	@FXML public JFXTextField lastnameField;
	@FXML public JFXTextField usernameField;
	@FXML public JFXTextField mostPlayedField;
	@FXML public JFXListView faveSongsList;
	@FXML public JFXListView favePlaylistList;
	@FXML public JFXListView songPlaylistList;
	@FXML public AnchorPane myProfilePane;
	@FXML public Circle profilePic;


	public ShowProfileView (Stage primaryStage, ProfileModel profilemodel, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;
		this.profilemodel = profilemodel;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("My Profile");

		init();
	}

	public void init()
	{
		//myProfilePane.getStyleClass().add("view/theme.css/.text-field");
		myProfilePane.getStylesheets().add("view/theme.css");
		firstNameField.getStyleClass().add("jfx-text-field-WhenDone");
		lastnameField.getStyleClass().add("jfx-text-field-WhenDone");
		usernameField.getStyleClass().add("jfx-text-field-WhenDone");

		if(profilemodel.getUser().getAvatarURL() == null) {
			Image defaultPic = new Image("resources/user.png");
			profilePic.setFill(new ImagePattern(defaultPic));
		}
		else{
			Image userPic = new Image(profilemodel.getUser().getAvatarURL().toString());
			profilePic.setFill(new ImagePattern(userPic));
		}

		firstNameField.setText(profilemodel.getUser().getFirst_name());
		lastnameField.setText(profilemodel.getUser().getLast_name());
		usernameField.setText(profilemodel.getUser().getUsername());
		mostPlayedField.setText("0");

		firstNameField.setEditable(false);
		lastnameField.setEditable(false);
		usernameField.setEditable(false);
		mostPlayedField.setEditable(false);
	}



	@Override
	public void Update(){}

	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == backBtn)
			controller.backToDashboard();
		else if (actionEvent.getSource() == logoutBtn)
			controller.logout();

	}

	public void editUserDetails(ActionEvent actionEvent) {
		controller.editUserDetails();

		if(editBtn.getText().equals("edit"))
		{
			firstNameField.getStyleClass().add("jfx-text-field-WhenEdit");
			lastnameField.getStyleClass().add("jfx-text-field-WhenEdit");
			usernameField.getStyleClass().add("jfx-text-field-WhenEdit");
			editBtn.setText("done");
			firstNameField.setEditable(true);
			lastnameField.setEditable(true);
			usernameField.setEditable(true);
		}
		else if(editBtn.getText().equals("done"))
		{
			firstNameField.getStyleClass().remove("jfx-text-field-WhenEdit");
			lastnameField.getStyleClass().remove("jfx-text-field-WhenEdit");
			usernameField.getStyleClass().remove("jfx-text-field-WhenEdit");
			editBtn.setText("edit");
			firstNameField.setEditable(false);
			lastnameField.setEditable(false);
			usernameField.setEditable(false);
		}

	}

	public void populateFavoritePlaylist(ArrayList<Playlist> playlists )
	{
		favePlaylistList.getStylesheets().add("view/theme.css");
		for(Playlist p : playlists){
			JFXButton playlistBtn = new JFXButton(p.getName());
			playlistBtn.getStyleClass().add("jfx-button-Playlist");
			favePlaylistList.getStyleClass().add("jfx-listView");

			favePlaylistList.getItems().add(playlistBtn);

		}
	}

	public void populateFavoriteSong(ArrayList<Song> songList)
	{
		for(Song s: songList){
			AnchorPane songAnchorPane = new AnchorPane();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			JFXButton playButton = new JFXButton();
			Text SongName = new Text(s.getSong_name());
			Text SongArtist = new Text("by " + s.getArtist_name());
			Text songYear = new Text(s.getYear()+"");
			Text songGenre = new Text(s.getGenre());

			SongName.setFont(Font.font("Poppins", 14));
			SongArtist.setFont(Font.font("Poppins", 12));
			songGenre.setFont(Font.font("Poppins", 12));
			songYear.setFont(Font.font("Poppins", 12));

			playView.setFitWidth(16);
			playView.setFitHeight(20);

			AnchorPane.setLeftAnchor(SongName, 50.0);
			AnchorPane.setBottomAnchor(SongName, 15.0);
			AnchorPane.setTopAnchor(SongArtist, 15.0);
			AnchorPane.setLeftAnchor(SongArtist, 50.0);
			AnchorPane.setLeftAnchor(songGenre, 400.0);
			AnchorPane.setBottomAnchor(songGenre, 15.0);
			AnchorPane.setTopAnchor(songYear, 15.0);
			AnchorPane.setLeftAnchor(songYear, 400.0);

			playButton.setGraphic(playView);
			songAnchorPane.getChildren().add(playButton);
			songAnchorPane.getChildren().add(SongName);
			songAnchorPane.getChildren().add(SongArtist);

		}
	}
}
