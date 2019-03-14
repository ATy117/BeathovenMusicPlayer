package populatorServices;

import com.jfoenix.controls.JFXButton;
import controller.DashboardController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model_rework.Song;

import java.util.ArrayList;
import java.util.List;

public class PopulateSongsAlbum implements PopulateSongs<AnchorPane> {

    private DashboardController controller;

    public PopulateSongsAlbum(DashboardController controller) {
        this.controller = controller;
    }

    @Override
    public List<AnchorPane> populateListView(List<Song> songlist) {
        List<AnchorPane> pane = new ArrayList<>();
        int index=0;
        for(Song s : songlist)
        {
            AnchorPane songInfo = new AnchorPane();
            JFXButton playBtn = new JFXButton();
            Image play = new Image("resources/play.png");
            ImageView playView = new ImageView(play);

            playView.setFitHeight(20);
            playView.setFitWidth(15);

            playBtn.setGraphic(playView);
            int finalIndex = index;
            playBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
            Text songTitle = new Text(s.getSong_name());
            Text genre = new Text(s.getGenre());

            songTitle.getStyleClass().add("text-input-PopulateTitle");
            genre.getStyleClass().add("text-input-PopulateInfo");


            AnchorPane.setLeftAnchor(songTitle, 45.0);
            AnchorPane.setTopAnchor(songTitle, 5.0);
            AnchorPane.setLeftAnchor(genre, 380.0);
            AnchorPane.setTopAnchor(genre, 5.0);

            songInfo.setPrefWidth(400.0);
            songInfo.setMaxWidth(400.0);

            songInfo.getChildren().add(genre);
            songInfo.getChildren().add(playBtn);
            songInfo.getChildren().add(songTitle);
            pane.add(songInfo);
            index++;
        }
        return pane;
    }
}
