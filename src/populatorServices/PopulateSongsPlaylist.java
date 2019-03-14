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
import model_rework.Song;

import java.util.ArrayList;
import java.util.List;

public class PopulateSongsPlaylist implements PopulateSongs<AnchorPane> {

    private DashboardController controller;
    private String playlistName;

    public PopulateSongsPlaylist(DashboardController controller, String playlistName) {
        this.controller = controller;
        this.playlistName = playlistName;
    }

    @Override
    public List<AnchorPane> populateListView(List<Song> songlist) {
        List<AnchorPane> pane = new ArrayList<>();
        JFXPopup songPlaylist = new JFXPopup();
        VBox vboxSongPlaylist = new VBox();
        int index=0;
        for(Song s : songlist)
        {
            AnchorPane songInfo = new AnchorPane();
            JFXButton playButton = new JFXButton();
            Image play = new Image("resources/play.png");
            ImageView playView = new ImageView(play);
            Text songName = new Text(s.getSong_name());
            Text songGenre = new Text(s.getGenre());
            Text songArtist = new Text(s.getArtist_name());
            Text songYear = new Text(s.getYear() + "");

            songName.getStyleClass().add("text-input-PopulateTitle");
            songGenre.getStyleClass().add("text-input-PopulateInfo");
            songArtist.getStyleClass().add("text-input-PopulateInfo");
            songYear.getStyleClass().add("text-input-PopulateInfo");

            playView.setFitWidth(15);
            playView.setFitHeight(20);

            AnchorPane.setLeftAnchor(songName, 50.0);
            AnchorPane.setTopAnchor(songName, 0.0);
            AnchorPane.setLeftAnchor(songGenre, 400.0);
            AnchorPane.setTopAnchor(songGenre, 0.0);
            AnchorPane.setTopAnchor(songArtist, 15.0);
            AnchorPane.setLeftAnchor(songArtist, 50.0);
            AnchorPane.setTopAnchor(songYear,15.0 );
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
            songInfo.getChildren().add(songYear);
            songInfo.getChildren().add(songArtist);
            songInfo.getChildren().add(songGenre);
            songInfo.getChildren().add(songName);
            songInfo.getChildren().add(playButton);
            pane.add(songInfo);

            songInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton()== MouseButton.SECONDARY) {
                        vboxSongPlaylist.getChildren().clear();
                        vboxSongPlaylist.getStylesheets().add("view/theme.css");
                        vboxSongPlaylist.getStyleClass().add("vBox-Pop");
                        JFXButton delete = new JFXButton("Delete from Playlist");
                        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                controller.deleteSongFromPlaylist(s.getUploader_id(), s.getSong_id(), playlistName);
                                songPlaylist.hide();
                            }
                        });
                        delete.getStyleClass().add("jfx-button-RightClick");
                        vboxSongPlaylist.getChildren().add(delete);
                        songPlaylist.setPopupContent(vboxSongPlaylist);
                        songPlaylist.show(songInfo, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                    }
                }
            });
            index++;
        }
        return pane;
    }
}
