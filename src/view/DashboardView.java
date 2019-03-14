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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_rework.*;
import populatorServices.PopulateSongs;
import populatorServices.PopulateSongsAlbum;
import populatorServices.PopulateSongsAll;
import populatorServices.PopulateSongsPlaylist;

import javax.imageio.stream.FileImageInputStream;
import java.sql.SQLOutput;
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
	@FXML public JFXListView<AnchorPane> populateSongsList;
	@FXML public JFXListView newPLaylistVbox;
	@FXML public JFXListView albumsVbox;
	@FXML public JFXComboBox filterCombo;
	@FXML public JFXTextField searchSongField;
	@FXML public Text headerLabelText;
	@FXML public AnchorPane headerInformation;
	@FXML public Circle userPic;
	@FXML public Text userName;
	@FXML public VBox songsVbox;
	@FXML public Text yearText;


	private VBox pbox = new VBox();
	private JFXPopup playlistEdit = new JFXPopup();
	private int popSource = 0;

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
			//albumText;
			genreText.setText(songplayermodel.getCurrentSong().getGenre());
			yearText.setText(year);
			//songPic.setGraphic;
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
		JFXTextField playlistField = new JFXTextField();
		playlistField.setPrefWidth(50);
		playlistField.setPromptText("Playlist Name");
		newPLaylistVbox.getItems().add(playlistField);
		playlistField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					newPLaylistVbox.getItems().remove(playlistField);
					String playlistName = playlistField.getText();
					if (playlistName.replaceAll("\\s+", "").equals("")){
						System.out.println("Empty playlist name field");
					} else {
						controller.addPlaylist(profilemodel.getUser().getUser_id(), playlistName);
					}
				}
			}
		});
	}

	public void populatePlaylist(ArrayList<Playlist> playlists){

		newPLaylistVbox.getStylesheets().add("view/theme.css");
		newPLaylistVbox.getItems().clear();
		for (Playlist p: playlists){
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
						playlistEdit.show(newPLaylistVbox, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
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
			newPLaylistVbox.getItems().add(playlistButton);
			newPLaylistVbox.getStyleClass().add("jfx-listView");
		}
	}


	public void populateAlbum(ArrayList<Album> albumList) {
		albumsVbox.getStylesheets().add("view/theme.css");
		albumsVbox.getItems().clear();
		for(Album a: albumList){
			JFXButton newAlbumBtn = new JFXButton(a.getName());
			newAlbumBtn.getStyleClass().add("jfx-button-Album");
			newAlbumBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					popSource = 2;
					controller.getAllSongsFromAlbum(profilemodel.getUser().getUser_id(), a.getAlbum_id());
					headerInformation.getChildren().clear();

					Circle albumPic = new Circle();
					Text albumArtist;
					Image imageAlbum;

					albumPic.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getButton() == MouseButton.SECONDARY)
								System.out.println("Change Photo");
								controller.editAlbum(a);
						}
					});

					if(a.getCover_URL() == null)
						imageAlbum = new Image("resources/music.png");
					else
						imageAlbum = new Image("resources/music.png");

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
		Image userPicImage;
		if(profilemodel.getUser() instanceof GuestUser)
			userName.setText("Guest");
		else
			userName.setText(profilemodel.getUser().getFirst_name() + " " + profilemodel.getUser().getLast_name() );
		if(profilemodel.getUser().getAvatarURL() == null){
			userPicImage = new Image("src/resources/user.png");
		}
		else {
			userPicImage = new Image("resources/" + profilemodel.getUser().getAvatarURL().getName());
		}

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
}

