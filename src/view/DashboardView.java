package view;

import com.jfoenix.controls.*;
import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.*;
import populatorServices.PopulateSongs;
import populatorServices.PopulateSongsAlbum;
import populatorServices.PopulateSongsAll;
import populatorServices.PopulateSongsPlaylist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DashboardView extends View {



	public Text statusSongText;
	public Text songTitleText;
	public Text singerText;
	public Text albumText;
	public Text genreText;
	public Circle songPic;

	private SongPlayerModel songplayermodel;
	private LibraryModel librarymodel;
	private ProfileModel profilemodel;

	@FXML public JFXButton myProfileBtn;
	@FXML public JFXButton uploadAddSongsBtn;
	@FXML public JFXButton addPlaylistBtn;
	@FXML public JFXListView<AnchorPane> populateSongsList;
	@FXML public JFXListView newPLaylistVbox;
	@FXML public JFXListView albumsVbox;
	@FXML public JFXComboBox filterCombo;
	@FXML public JFXTextField searchSongField;
	@FXML public Text headerLabelText;
	@FXML public AnchorPane headerInformation;
	@FXML public AnchorPane mainPane;
	@FXML public Circle userPic;
	@FXML public Text userName;
	@FXML public VBox songsVbox;
	@FXML public Text yearText;
	@FXML public VBox playlistVbox;


	private VBox pbox = new VBox();
	private JFXPopup playlistEdit = new JFXPopup();
	private int popSource = 0;

	private JFXPopup errorPopup = new JFXPopup();
	private AnchorPane errorAnchor = new AnchorPane();

	private PopulateSongs<AnchorPane> populateSongs;

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


		searchSong();
		Update();
		init();
		//userInfo();
	}

	@Override
	public void Update() {
		if (popSource == 0) {
			populateSongs = new PopulateSongsAll(controller, librarymodel, profilemodel);
		} else if (popSource == 1){
			populateSongs = new PopulateSongsPlaylist(controller, headerLabelText.getText());
		} else if (popSource == 2){
			populateSongs = new PopulateSongsAlbum(controller);
		}

		populateSongsList.getItems().clear();
		populateSongsList.getStylesheets().add("view/theme.css");
		populateSongsList.getStyleClass().add("jfx-list-cell");
		populateSongsList.getItems().addAll(populateSongs.populateListView(librarymodel.getSongList()));

		populateAlbum((ArrayList<Album>)librarymodel.getAlbumList());
		populatePlaylist((ArrayList<Playlist>)librarymodel.getPlaylistList());
		userInfo();
		setSongInfo();
	}

	private void setSongInfo() {
		if (songplayermodel.getCurrentSong() != null) {
			String year = String.valueOf(songplayermodel.getCurrentSong().getYear());
			statusSongText.setText("Now Playing");
			songTitleText.setText(songplayermodel.getCurrentSong().getSong_name());
			singerText.setText(songplayermodel.getCurrentSong().getArtist_name());

			Album album = controller.getAlbumOfSong(songplayermodel.getCurrentSong().getAlbum_id());

			if (album != null) {
				albumText.setText("Album: " + album.getName());
			}
			else {
				albumText.setText("Album: None");
			}

			genreText.setText(songplayermodel.getCurrentSong().getGenre());
			yearText.setText(year);

			Image albumpic = controller.getImageFromAlbum(songplayermodel.getCurrentSong().getAlbum_id());
			songPic.setFill(new ImagePattern(albumpic));
		}
	}

	public void changePane(ActionEvent actionEvent) {
		if (actionEvent.getSource() == myProfileBtn) {
			controller.viewProfile();
		}
		else if (actionEvent.getSource() == uploadAddSongsBtn) {
			popSource = 0;
			controller.uploadSong();
		}
	}

	public void createPlaylist(ActionEvent actionEvent) {
		playlistVbox.getStylesheets().add("view/theme.css");
		playlistVbox.getChildren().remove(addPlaylistBtn);
		playlistVbox.getChildren().remove(newPLaylistVbox);
		JFXTextField playlistField = new JFXTextField();
		playlistField.setPrefWidth(50);
		playlistField.setPromptText("Playlist Name");
		playlistField.getStyleClass().add("jfx-text-field-Search");
		playlistVbox.getChildren().add(playlistField);
		playlistVbox.getChildren().add(newPLaylistVbox);
		playlistField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					playlistVbox.getChildren().remove(playlistField);
					playlistVbox.getChildren().remove(newPLaylistVbox);
					playlistVbox.getChildren().add(addPlaylistBtn);
					playlistVbox.getChildren().add(newPLaylistVbox);
					String playlistName = playlistField.getText();
					if (playlistName.replaceAll("\\s+", "").equals("")){
						popUpError("Empty Playlist Name");
					} else {
						if(controller.addPlaylist(profilemodel.getUser().getUser_id(), playlistName)== false){
							popUpError("Playlist already Exists");
						}
					}
				}
			}
		});
	}

	public void populatePlaylist(ArrayList<Playlist> playlists){

		newPLaylistVbox.getStylesheets().add("view/theme.css");
		newPLaylistVbox.getItems().clear();
		for (Playlist p: playlists){
			AnchorPane playlistAnchor = new AnchorPane();
			JFXButton playlistButton = new JFXButton(p.getName());
			playlistButton.getStyleClass().add("jfx-button-Playlist");
			playlistButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (event.getButton() == MouseButton.SECONDARY){
						pbox.getChildren().clear();
						pbox.getStylesheets().add("view/theme.css");
						pbox.getStyleClass().add("vBox-Pop");
						JFXButton delete = new JFXButton("Delete");
						delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								popSource = 0;
								playlistEdit.hide();
								controller.deletePlaylist(p.getUser_id(), p.getPlaylist_id());
							}
						});
						delete.getStyleClass().add("jfx-button-RightClick");

						JFXButton favorite = new JFXButton();
						if (p.isFavorite()){
							favorite.setText("Unfavorite");
							favorite.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									p.setFavorite(false);
									playlistEdit.hide();
									controller.editPlaylist(p);
								}
							});
						} else {
							favorite.setText("Favorite");
							favorite.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									p.setFavorite(true);
									playlistEdit.hide();
									controller.editPlaylist(p);
								}
							});
						}
						favorite.getStyleClass().add("jfx-button-RightClick");

						pbox.getChildren().add(delete);
						if (profilemodel.getUser() instanceof RegisteredUser)
							pbox.getChildren().add(favorite);

						playlistEdit.setPopupContent(pbox);
						playlistEdit.show(playlistAnchor, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					} else {
						popSource = 1;
						controller.getAllSongsFromPlaylist(profilemodel.getUser().getUser_id(), p.getPlaylist_id());
						headerLabelText.setText(p.getName());

						JFXButton play = new JFXButton();

						play.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								List<Song> playablelist = librarymodel.getSongList();
								controller.playSong(playablelist);

							}
						});

						Image playImg = new Image("resources/circlePlay.png");
						ImageView playView = new ImageView(playImg);
						play.setGraphic(playView);

						playView.setFitHeight(50);
						playView.setFitWidth(50);


						AnchorPane.setLeftAnchor(play, 32.0);
						AnchorPane.setTopAnchor(play, 6.0);

						headerInformation.getChildren().clear();
						headerInformation.getChildren().add(play);
						headerInformation.getChildren().remove(uploadAddSongsBtn);
						headerInformation.getChildren().add(headerLabelText);
						headerInformation.getChildren().add(filterCombo);
						filterCombo.setValue(null);
					}
				}
			});
			playlistAnchor.getChildren().add(playlistButton);
			newPLaylistVbox.getItems().add(playlistAnchor);
			newPLaylistVbox.getStyleClass().add("jfx-listView");
		}
	}


	public void populateAlbum(ArrayList<Album> albumList) {
		albumsVbox.getStylesheets().add("view/theme.css");
		albumsVbox.getItems().clear();
		for(Album a: albumList){
			JFXButton newAlbumBtn = new JFXButton(a.getName());
			newAlbumBtn.getStyleClass().add("jfx-button-Album");
			newAlbumBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					popSource = 2;
					controller.getAllSongsFromAlbum(profilemodel.getUser().getUser_id(), a.getAlbum_id());
					headerInformation.getChildren().clear();

					Circle albumPic = new Circle();
					Text albumArtist;
					Image imageAlbum;

					imageAlbum = new Image(a.getCover_URL().toURI().toString());


					albumPic.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							File photofile = controller.selectPhoto();
							if (photofile != null) {
								popSource = 2;
								a.setCover_URL(photofile);
								controller.editAlbum(a);
								newAlbumBtn.fire();
							}
						}
					});


					albumArtist = new Text("by " + a.getArtist_name());
					albumArtist.getStyleClass().add("text-input-Artist");

					albumPic.setFill(new ImagePattern(imageAlbum));
					albumPic.setRadius(25);
					AnchorPane.setLeftAnchor(albumPic, 40.0);
					AnchorPane.setTopAnchor(albumPic, 10.0);
					AnchorPane.setLeftAnchor(albumArtist,130.0 );
					AnchorPane.setTopAnchor(albumArtist, 33.0);

					headerLabelText.setText(a.getName());
					headerInformation.getChildren().add(albumArtist);
					headerInformation.getChildren().add(headerLabelText);
					headerInformation.getChildren().add(albumPic);
					headerInformation.getChildren().remove(uploadAddSongsBtn);

				}
			});
			albumsVbox.getItems().add(newAlbumBtn);
			albumsVbox.getStyleClass().add("jfx-listView");
		}

	}

	public void showMusicPlayer() {
		controller.showSongPlayer();
	}


	private void userInfo()
	{

		if(profilemodel.getUser() instanceof GuestUser)
			userName.setText("Guest");
		else
			userName.setText(profilemodel.getUser().getFirst_name() + " " + profilemodel.getUser().getLast_name() );


		Image userPicImage = controller.getImageFromUser(profilemodel.getUser());


		userPic.setFill(new ImagePattern(userPicImage));
	}

	public void searchSong()
	{
		songsVbox.getStylesheets().add("view/theme.css");
		searchSongField.getStyleClass().add("jfx-text-field-Search");

		searchSongField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					controller.searchSong(searchSongField.getText());
					searchSongField.setText("");
				}
			}
		});

	}

	private void init()
	{
		statusSongText.setText("No Song Playing");
		songTitleText.setText("");
		albumText.setText("");
		genreText.setText("");
		singerText.setText("");
		yearText.setText("");

		headerInformation.getStylesheets().add("view/theme.css");
		filterCombo.getStyleClass().add("jfx-combo-box-Filter");
		filterCombo.getItems().add("Title");
		filterCombo.getItems().add("Genre");
		filterCombo.getItems().add("Album");
		filterCombo.getItems().add("Year");
		filterCombo.getItems().add("Artist");
		filterCombo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.sortSongs((String)filterCombo.getValue());
			}
		});
	}

	public void getAllSongs (ActionEvent actionEvent){
		popSource = 0;
		controller.getAllSongs(profilemodel.getUser().getUser_id());
		headerLabelText.setText("All Songs");
		headerInformation.getChildren().clear();
		headerInformation.getChildren().add(uploadAddSongsBtn);
		headerInformation.getChildren().add(headerLabelText);
		headerInformation.getChildren().add(filterCombo);
		filterCombo.setValue(null);

	}

	public void logout(ActionEvent actionEvent) {
		controller.logout();
	}

	public void popUpError(String errorMessageText)
	{
		errorAnchor.getStylesheets().add("view/theme.css");
		errorAnchor.getStyleClass().add("anchorPane-Error");

		Image error = new Image("resources/error.png");
		ImageView errorView = new ImageView(error);
		Text errorMessage = new Text(errorMessageText);
		errorMessage.getStyleClass().add("text-input-Error");
		AnchorPane.setTopAnchor(errorMessage, 93.0);
		AnchorPane.setLeftAnchor(errorMessage, 20.0);
		AnchorPane.setTopAnchor(errorView, 30.0);
		AnchorPane.setLeftAnchor(errorView, 27.0);
		errorAnchor.getChildren().add(errorView);
		errorAnchor.getChildren().add(errorMessage);

		errorAnchor.setMinSize(220.0, 150.0);
		errorAnchor.setMaxSize(220.0, 150.0);
		errorPopup.setPopupContent(errorAnchor);
		errorPopup.show(mainPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
	}
}

