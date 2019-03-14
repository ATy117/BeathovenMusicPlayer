package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.Playlist;
import model_rework.ProfileModel;
import model_rework.Song;
import model_rework.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	private JFXPopup errorPopup = new JFXPopup();
	private AnchorPane errorAnchor = new AnchorPane();

	public ShowProfileView (Stage primaryStage, ProfileModel profilemodel, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;
		this.profilemodel = profilemodel;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("My Profile");

		Update();
		init();
	}

	public void init()
	{
		//myProfilePane.getStyleClass().add("view/theme.css/.text-field");
		myProfilePane.getStylesheets().add("view/theme.css");
		firstNameField.getStyleClass().add("jfx-text-field-WhenDone");
		lastnameField.getStyleClass().add("jfx-text-field-WhenDone");
		usernameField.getStyleClass().add("jfx-text-field-WhenDone");


		firstNameField.setText(profilemodel.getUser().getFirst_name());
		lastnameField.setText(profilemodel.getUser().getLast_name());
		usernameField.setText(profilemodel.getUser().getUsername());
		if (profilemodel.getMostPlayedSong() == null)
			mostPlayedField.setText("None");
		else
			mostPlayedField.setText(profilemodel.getMostPlayedSong().getSong_name());

		firstNameField.setEditable(false);
		lastnameField.setEditable(false);
		usernameField.setEditable(false);
		mostPlayedField.setEditable(false);

		profilePic.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				File photoFile = controller.selectPhoto();
				if (photoFile != null){
					User u = profilemodel.getUser();
					u.setAvatarURL(photoFile);
					controller.editUser(u);
				}
			}
		});
	}



	@Override
	public void Update(){
		populateFavoriteSong((ArrayList<Song>) profilemodel.getFavoriteSongs());
		populateFavoritePlaylist((ArrayList<Playlist>) profilemodel.getFavoritePlaylists());
		populateSongFromPlayist((ArrayList<Song>) profilemodel.getPlaylistSongs());

		Image userPic = controller.getImageFromUser(profilemodel.getUser());
		profilePic.setFill(new ImagePattern(userPic));
	}

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
			editBtn.setText("done");
			firstNameField.setEditable(true);
			lastnameField.setEditable(true);

		}
		else if(editBtn.getText().equals("done"))
		{
			firstNameField.getStyleClass().remove("jfx-text-field-WhenEdit");
			lastnameField.getStyleClass().remove("jfx-text-field-WhenEdit");
			editBtn.setText("edit");
			firstNameField.setEditable(false);
			lastnameField.setEditable(false);

			String newfirst = firstNameField.getText();
			String newlast = lastnameField.getText();

			String firstcheck = newfirst.replaceAll("\\s+", "");
			String lastcheck = newlast.replaceAll("\\s+", "");

			if (firstcheck.equals("") || lastcheck.equals("")){
				errorAnchor.getStylesheets().add("view/theme.css");
				errorAnchor.getStyleClass().add("anchorPane-Error");

				Image error = new Image("resources/error.png");
				ImageView errorView = new ImageView(error);
				Text errorMessage = new Text("Empty Fields");
				errorMessage.getStyleClass().add("text-input-Error");
				AnchorPane.setTopAnchor(errorMessage, 93.0);
				AnchorPane.setLeftAnchor(errorMessage, 50.0);
				AnchorPane.setTopAnchor(errorView, 30.0);
				AnchorPane.setLeftAnchor(errorView, 27.0);
				errorAnchor.getChildren().add(errorView);
				errorAnchor.getChildren().add(errorMessage);

				errorAnchor.setMinSize(220.0, 150.0);
				errorAnchor.setMaxSize(220.0, 150.0);
				errorPopup.setPopupContent(errorAnchor);
				errorPopup.show(myProfilePane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
			} else {
				User RU = profilemodel.getUser();
				RU.setFirst_name(newfirst);
				RU.setLast_name(newlast);
				controller.editUser(RU);
			}
		}



	}

	public void populateFavoritePlaylist(ArrayList<Playlist> playlists )
	{
		favePlaylistList.getItems().clear();
		favePlaylistList.getStylesheets().add("view/theme.css");
		for(Playlist p : playlists){
			JFXButton playlistBtn = new JFXButton(p.getName());
			playlistBtn.getStyleClass().add("jfx-button-FavePlaylist");

			playlistBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					controller.getSongFromPlaylist(profilemodel.getUser().getUser_id(), p.getPlaylist_id());

				}
			});

			favePlaylistList.getStyleClass().add("jfx-listView");
			favePlaylistList.getItems().add(playlistBtn);

		}
	}

	public void populateFavoriteSong(ArrayList<Song> songList)
	{
		faveSongsList.getItems().clear();
		faveSongsList.getStylesheets().add("view/theme.css");
		faveSongsList.getStyleClass().add("jfx-listView");
		int index=0;
		for(Song s: songList){
			AnchorPane songAnchorPane = new AnchorPane();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			JFXButton playButton = new JFXButton();
			Text SongName = new Text(s.getSong_name());
			Text SongArtist = new Text("by " + s.getArtist_name());

			SongName.setFont(Font.font("Poppins", 14));
			SongArtist.setFont(Font.font("Poppins", 12));
			SongName.getStyleClass().add("text-input-PopulateTitle");
			SongArtist.getStyleClass().add("text-input-PopulateInfo");

			playView.setFitWidth(16);
			playView.setFitHeight(20);

			AnchorPane.setLeftAnchor(SongName, 50.0);
			AnchorPane.setBottomAnchor(SongName, 15.0);
			AnchorPane.setTopAnchor(SongArtist, 15.0);
			AnchorPane.setLeftAnchor(SongArtist, 50.0);

			playButton.setGraphic(playView);

			int finalIndex = index;
			playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					List<Song> playableList = new ArrayList<>();

					for (int i = finalIndex; i <songList.size(); i++) {
						playableList.add(songList.get(i));
					}

					for (int i = 0; i<finalIndex; i++) {
						playableList.add(songList.get(i));
					}

					controller.playSong(playableList);

				}
			});

			index++;

			songAnchorPane.getChildren().add(playButton);
			songAnchorPane.getChildren().add(SongName);
			songAnchorPane.getChildren().add(SongArtist);
			faveSongsList.getItems().add(songAnchorPane);
		}
	}

	public void populateSongFromPlayist(ArrayList<Song> playlistSongs)
	{
		songPlaylistList.getItems().clear();
		songPlaylistList.getStylesheets().add("view/theme.css");
		songPlaylistList.getStyleClass().add("jfx-listView");
		int index=0;
		for(Song s: playlistSongs){
			AnchorPane songInfo = new AnchorPane();
			JFXButton playBtn = new JFXButton();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			Text songName = new Text(s.getSong_name());
			Text artistName = new Text(s.getArtist_name());
			playView.setFitWidth(15);
			playView.setFitHeight(20);

			songName.getStyleClass().add("text-input-PopulateTitle");
			artistName.getStyleClass().add("text-input-PopulateInfo");

			playBtn.setGraphic(playView);
			int finalIndex = index;
			playBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					List<Song> playableList = new ArrayList<>();

					for (int i = finalIndex; i <playlistSongs.size(); i++) {
						playableList.add(playlistSongs.get(i));
					}

					for (int i = 0; i<finalIndex; i++) {
						playableList.add(playlistSongs.get(i));
					}

					controller.playSong(playableList);

				}
			});

			index++;

			AnchorPane.setLeftAnchor(songName, 50.0);
			AnchorPane.setBottomAnchor(songName, 15.0);
			AnchorPane.setTopAnchor(artistName, 15.0);
			AnchorPane.setLeftAnchor(artistName, 50.0);

			songInfo.getChildren().add(playBtn);
			songInfo.getChildren().add(songName);
			songInfo.getChildren().add(artistName);
			songPlaylistList.getItems().add(songInfo);
		}

	}
}
