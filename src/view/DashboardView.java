package view;

import com.jfoenix.controls.*;
import controller.DashboardController;
import controller.RegisteredUserController;
import controller.SongPlayerController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model_rework.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Color.WHITE;

public class DashboardView extends View {

	private SongPlayerModel songplayermodel;
	private LibraryModel librarymodel;
	private ProfileModel profilemodel;

	@FXML public JFXButton myProfileBtn;
	@FXML public JFXButton uploadAddSongsBtn;
	@FXML public JFXListView populateSongsList;
	@FXML public JFXListView newPLaylistVbox;
	@FXML public JFXListView albumsVbox;
	@FXML public JFXComboBox filterCombo;
	@FXML public JFXTextField searchSongField;
	@FXML public Text headerLabelText;
	@FXML public AnchorPane headerInformation;
	@FXML public Circle userPic;
	@FXML public Text userName;
	@FXML public VBox songsVbox;


	public JFXPopup songEdit = new JFXPopup();
	public VBox vbox = new VBox();
	public JFXPopup addToPlaylistPop = new JFXPopup();
	public VBox playlistVboxList = new VBox();

	private VBox pbox = new VBox();
	private JFXPopup playlistEdit = new JFXPopup();
	private int popSource = 0;


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
			populateSong((ArrayList<Song>) librarymodel.getSongList());
		} else if (popSource == 1){
			populateSongPlaylistVersion((ArrayList<Song>) librarymodel.getSongList());
		} else if (popSource == 2){
			populateSongAlbumVersion((ArrayList<Song>) librarymodel.getSongList());
		}

		populateAlbum((ArrayList<Album>)librarymodel.getAlbumList());
		populatePlaylist((ArrayList<Playlist>)librarymodel.getPlaylistList());
		userInfo();
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
					System.out.println(playlistField.getText());
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
		controller.sayHi();
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
						System.out.println("Detect right Click");
						JFXButton delete = new JFXButton("Delete");
						delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								popSource = 0;
								playlistEdit.hide();
								controller.deletePlaylist(p.getUser_id(), p.getPlaylist_id());
							}
						});

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

						pbox.getChildren().add(delete);
						if (profilemodel.getUser() instanceof RegisteredUser)
							pbox.getChildren().add(favorite);

						playlistEdit.setPopupContent(pbox);
						playlistEdit.show(newPLaylistVbox, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					} else {
						popSource = 1;
						controller.getAllSongsFromPlaylist(profilemodel.getUser().getUser_id(), p.getPlaylist_id());
						headerLabelText.setText(p.getName());
						headerInformation.getChildren().remove(uploadAddSongsBtn);
					}
				}
			});
			newPLaylistVbox.getItems().add(playlistButton);
			newPLaylistVbox.getStyleClass().add("jfx-listView");
		}
	}


	public void populateAlbum(ArrayList<Album> albumList)
	{
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
					Circle albumPic = new Circle();
					Image imageAlbum;
					if(a.getCover_URL() == null)
						imageAlbum = new Image("resources/music.png");
					else
						imageAlbum = new Image(a.getCover_URL().toString());

					albumPic.setFill(new ImagePattern(imageAlbum));
					albumPic.setRadius(25);
					AnchorPane.setLeftAnchor(albumPic, 40.0);
					AnchorPane.setTopAnchor(albumPic, 10.0);
					headerInformation.getChildren().add(albumPic);
					headerInformation.getChildren().remove(uploadAddSongsBtn);
					headerLabelText.setText(a.getName());
				}
			});
			albumsVbox.getItems().add(newAlbumBtn);
			albumsVbox.getStyleClass().add("jfx-listView");
		}

	}

	/*public void albumCreated(String name)
	{
		albumsVbox.getStylesheets().add("view/theme.css");
		JFXButton newAlbum = new JFXButton(name);
		albumsVbox.getItems().add(newAlbum);
	}*/

	public void showMusicPlayer() {
		controller.showSongPlayer();
	}

	public void populateSong(ArrayList<Song> songlist){
		populateSongsList.getItems().clear();
		for(Song s : songlist)
		{
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
			songAnchorPane.getChildren().add(SongArtist);
			songAnchorPane.getChildren().add(songGenre);
			songAnchorPane.getChildren().add(songYear);
			songAnchorPane.getChildren().add(SongName);
			songAnchorPane.getChildren().add(playButton);
			populateSongsList.getItems().add(songAnchorPane);
			populateSongsList.getStylesheets().add("view/theme.css");
			populateSongsList.getStyleClass().add("anchorPane");
			populateSongsList.getStyleClass().add("jfx-listView");

			songAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY)
					{
						System.out.println("Detect right Click");
						vbox.getChildren().clear();

						JFXButton delete = new JFXButton("Delete");
						delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								songEdit.hide();
								controller.deleteSong(s.getUploader_id(), s.getSong_id(), s.getAlbum_id());
							}
						});

						JFXButton favorite = new JFXButton();
						if (s.isFavorite()){
							favorite.setText("Unfavorite");
							favorite.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									s.setFavorite(false);
									songEdit.hide();
									controller.editSong(s);
								}
							});
						} else {
							favorite.setText("Favorite");
							favorite.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									s.setFavorite(true);
									songEdit.hide();
									controller.editSong(s);
								}
							});
						}


						JFXButton addPlaylist = new JFXButton("Add to Playlist");
						addPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								playlistVboxList.getChildren().clear();

								for(Playlist p: librarymodel.getPlaylistList())
								{
									JFXButton playlistButton = new JFXButton(p.getName());
									playlistVboxList.getChildren().add(playlistButton);

									playlistButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent event) {
											controller.addSongToPlaylist(s,p);
											addToPlaylistPop.hide();

										}
									});
								}
								addToPlaylistPop.setPopupContent(playlistVboxList);
								addToPlaylistPop.show(vbox, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT);

							}
						});

						vbox.getChildren().add(delete);
						if (profilemodel.getUser() instanceof RegisteredUser)
							vbox.getChildren().add(favorite);
						vbox.getChildren().add(addPlaylist);
						songEdit.setPopupContent(vbox);
						songEdit.show(songAnchorPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);

					}
				}
			});
		}

	}

//	private void popUpInit()
//	{
//		vbox.getChildren().clear();
//
//		JFXButton delete = new JFXButton("Delete");
//		JFXButton favorite = new JFXButton("Favorite");
//		JFXButton addPlaylist = new JFXButton("Add to Playlist");
//		vbox.getChildren().add(delete);
//		vbox.getChildren().add(favorite);
//		vbox.getChildren().add(addPlaylist);
//		songEdit.setPopupContent(vbox);
//		songEdit.show(populateSongsList, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
//
//	}

	public void populateSongPlaylistVersion(ArrayList<Song> songlist){
		populateSongsList.getItems().clear();
		populateSongsList.getStylesheets().add("view/theme.css");
		for(Song s : songlist)
		{
			HBox hbox = new HBox();
			Text songName = new Text(s.getSong_name());
			Text space = new Text("        ");
			Text space2 = new Text("        ");
			Text space3 = new Text("        ");
			Text space4 = new Text("        ");
			Text songArtist = new Text(s.getArtist_name());
			Text genre = new Text(s.getGenre());
			Text year = new Text(""+s.getYear());

			JFXButton playButton = new JFXButton();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			playView.setFitWidth(15);
			playView.setFitHeight(20);
			playButton.setGraphic(playView);

			songName.setFont(Font.font("Poppins", 14));
			songArtist.setFont(Font.font("Poppins", 14));
			genre.setFont(Font.font("Poppins", 14));
			year.setFont(Font.font("Poppins", 14));

			hbox.getChildren().add(playButton);
			hbox.getChildren().add(space2);
			hbox.getChildren().add(songName);
			hbox.getChildren().add(space);
			hbox.getChildren().add(songArtist);
			hbox.getChildren().add(space3);
			hbox.getChildren().add(genre);
			hbox.getChildren().add(space4);
			hbox.getChildren().add(year);
			populateSongsList.getItems().add(hbox);
		}
	}

	public void populateSongAlbumVersion(ArrayList<Song> songlist){
		populateSongsList.getItems().clear();
		for(Song s : songlist)
		{
			HBox hbox = new HBox();
			Text songName = new Text(s.getSong_name());
			Text space = new Text("        ");
			Text space2 = new Text("        ");
			Text space3 = new Text("        ");
			Text space4 = new Text("        ");
			Text songArtist = new Text(s.getArtist_name());
			Text genre = new Text(s.getGenre());
			Text year = new Text(""+s.getYear());

			JFXButton playButton = new JFXButton();
			Image play = new Image("resources/play.png");
			ImageView playView = new ImageView(play);
			playView.setFitWidth(15);
			playView.setFitHeight(20);
			playButton.setGraphic(playView);

			songName.setFont(Font.font("Poppins", 14));
			songArtist.setFont(Font.font("Poppins", 14));
			genre.setFont(Font.font("Poppins", 14));
			year.setFont(Font.font("Poppins", 14));


			hbox.getChildren().add(playButton);
			hbox.getChildren().add(space2);
			hbox.getChildren().add(songName);
			hbox.getChildren().add(space);
			hbox.getChildren().add(songArtist);
			hbox.getChildren().add(space3);
			hbox.getChildren().add(genre);
			hbox.getChildren().add(space4);
			hbox.getChildren().add(year);
			populateSongsList.getItems().add(hbox);
		}
	}

	private void userInfo()
	{
		Image userPicImage;
		if(profilemodel.getUser() instanceof GuestUser)
			userName.setText("Guest");
		else
			userName.setText(profilemodel.getUser().getFirst_name() + " " + profilemodel.getUser().getLast_name() );
		if(profilemodel.getUser().getAvatarURL() == null){
			userPicImage = new Image("resources/user.png");

		}
		else
			userPicImage = new Image(profilemodel.getUser().getAvatarURL().toString());

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
		filterCombo.getItems().add("Genre");
		filterCombo.getItems().add("Album");
		filterCombo.getItems().add("Year");

	}

	public void getAllSongs (ActionEvent actionEvent){
		popSource = 0;
		controller.getAllSongs(profilemodel.getUser().getUser_id());
		headerLabelText.setText("All Songs");
		headerInformation.getChildren().clear();
		headerInformation.getChildren().add(uploadAddSongsBtn);
		headerInformation.getChildren().add(headerLabelText);
		headerInformation.getChildren().add(filterCombo);

	}

	public void logout(ActionEvent actionEvent) {
		controller.logout();
	}
}

