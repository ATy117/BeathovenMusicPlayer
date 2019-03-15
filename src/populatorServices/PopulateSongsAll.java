package populatorServices;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import controller.DashboardController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model_rework.*;

import java.util.ArrayList;
import java.util.List;

public class PopulateSongsAll implements PopulateSongs<AnchorPane> {

    private DashboardController controller;
    private LibraryModel librarymodel;
    private ProfileModel profilemodel;

    private JFXPopup errorPopup = new JFXPopup();
    private AnchorPane errorAnchor = new AnchorPane();

    public PopulateSongsAll(DashboardController controller, LibraryModel librarymodel, ProfileModel profileModel) {
        this.controller = controller;
        this.librarymodel = librarymodel;
        this.profilemodel = profileModel;
    }

    @Override
    public List<AnchorPane> populateListView(List<Song> songlist) {
        List<AnchorPane> pane = new ArrayList<>();
        JFXPopup songEdit = new JFXPopup();
        VBox vbox = new VBox();
        VBox playlistVboxList = new VBox();
        JFXPopup addToPlaylistPop = new JFXPopup();
        int index = 0;
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

            int finalIndex = index;
            playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    List<Song> playableList = new ArrayList<>();

                    for (int i = finalIndex; i <songlist.size(); i++) {
                        playableList.add(songlist.get(i));
                    }

                    for (int i = 0; i<finalIndex; i++) {
                        playableList.add(songlist.get(i));
                    }

                    controller.playSong(playableList);

                }
            });

            songAnchorPane.getChildren().add(SongArtist);
            songAnchorPane.getChildren().add(songGenre);
            songAnchorPane.getChildren().add(songYear);
            songAnchorPane.getChildren().add(SongName);
            songAnchorPane.getChildren().add(playButton);

            SongName.getStyleClass().add("text-input-PopulateTitle");
            songGenre.getStyleClass().add("text-input-PopulateInfo");
            songYear.getStyleClass().add("text-input-PopulateInfo");
            SongArtist.getStyleClass().add("text-input-PopulateInfo");

            songAnchorPane.getStylesheets().add("view/theme.css");
            pane.add(songAnchorPane);
            songEdit.getStyleClass().add("jfx-popup-Pop");
            songAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton() == MouseButton.SECONDARY)
                    {
                        vbox.getChildren().clear();
                        vbox.getStylesheets().add("view/theme.css");
                        vbox.getStyleClass().add("vBox-Pop");

                        JFXButton delete = new JFXButton("Delete");
                        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                songEdit.hide();
                                if(controller.deleteSong(s.getUploader_id(), s.getSong_id(), s.getAlbum_id())== false)
                                {
                                    errorAnchor.getStylesheets().add("view/theme.css");
                                    errorAnchor.getStyleClass().add("anchorPane-Error");

                                    Image error = new Image("resources/error.png");
                                    ImageView errorView = new ImageView(error);
                                    Text errorMessage = new Text("Song is Currently Playing");
                                    errorMessage.getStyleClass().add("text-input-Error");
                                    AnchorPane.setTopAnchor(errorMessage, 93.0);
                                    AnchorPane.setLeftAnchor(errorMessage, 10.0);
                                    AnchorPane.setTopAnchor(errorView, 30.0);
                                    AnchorPane.setLeftAnchor(errorView, 27.0);
                                    errorAnchor.getChildren().add(errorView);
                                    errorAnchor.getChildren().add(errorMessage);

                                    errorAnchor.setMinSize(240.0, 150.0);
                                    errorAnchor.setMaxSize(240.0, 150.0);
                                    errorPopup.setPopupContent(errorAnchor);
                                    errorPopup.show(songAnchorPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                                }
                            }
                        });
                        delete.getStyleClass().add("jfx-button-RightClick");

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
                        favorite.getStyleClass().add("jfx-button-RightClick");

                        JFXButton addPlaylist = new JFXButton("Add to Playlist");
                        addPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                playlistVboxList.getChildren().clear();
                                playlistVboxList.getStylesheets().add("view/theme.css");
                                playlistVboxList.getStyleClass().add("vBox-PopPlaylist");

                                for(Playlist p: librarymodel.getPlaylistList())
                                {
                                    JFXButton playlistButton = new JFXButton(p.getName());
                                    playlistVboxList.getChildren().add(playlistButton);

                                    playlistButton.getStyleClass().add("jfx-button-RightPlaylist");
                                    playlistButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            controller.addSongToPlaylist(s.getUploader_id(), s.getSong_id(), p.getPlaylist_id());
                                            addToPlaylistPop.hide();
                                            songEdit.hide();
                                        }
                                    });
                                }


                                addToPlaylistPop.setPopupContent(playlistVboxList);
                                addToPlaylistPop.show(vbox, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT);

                            }
                        });
                        addPlaylist.getStyleClass().add("jfx-button-RightClick");

                        JFXButton edit = new JFXButton("Edit");
                        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                controller.editSong(s);
                            }
                        });
                        edit.getStyleClass().add("jfx-button-RightClick");
                        vbox.getChildren().add(delete);
                        if (profilemodel.getUser() instanceof RegisteredUser)
                            vbox.getChildren().add(favorite);
                        vbox.getChildren().add(addPlaylist);
                        //vbox.getChildren().add(edit);
                        songEdit.setPopupContent(vbox);
                        songEdit.show(songAnchorPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                    }
                }
            });
            index++;
        }
        return pane;
    }
}
