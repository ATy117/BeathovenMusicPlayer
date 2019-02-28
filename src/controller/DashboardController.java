package controller;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Track;
import org.controlsfx.control.PopOver;

public class DashboardController extends Controller{

	public void playSong() {}

	public void populateSongList(){
		// Song list renderer
		for (Track song: parent.songlist){
			HBox songCell = new HBox();
			songCell.setSpacing(20);

			ImageView cover = new ImageView(new Image(getClass().getResourceAsStream("/resources/blank_album.jpg")));
			cover.setFitHeight(40);
			cover.setFitWidth(40);


			Label title = new Label(song.getName());
			Label artist = new Label ("Artist ");
			Label duration = new Label ("00:00");

			title.setPrefWidth(250);
			artist.setPrefWidth(220);
			duration.setPrefWidth(40);

			duration.setOpacity(0.50);



			Button play = new Button(" ");
			play.getStylesheets().add(getClass().getResource("theme.css").toString());
			play.getStyleClass().add("play-button");
			play.setMinHeight(32);
			play.setMinWidth(32);


			Button plus = new Button("+");

			plus.setOnMouseClicked(e -> {

				AnchorPane details = new AnchorPane();
				details.setMinWidth(150);
				details.setMinHeight(150);


				PopOver songDetails = new PopOver();

				songDetails.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
				songDetails.setContentNode(details);
				songDetails.setAutoFix(true);
				songDetails.setAutoHide(true);
				songDetails.setHideOnEscape(true);
				songDetails.setDetachable(false);
				songDetails.show(plus);
			});


			songCell.getChildren().addAll(play, cover, duration, title, artist ,plus);
			songsListView.getItems().add(songCell);

			songCell.setOnMouseClicked(e -> {
				if(e.getButton().equals(MouseButton.PRIMARY)){
					if(e.getClickCount() == 2){
						System.out.println("Play " + song.getName());
						parent.sayHi();
					}
				}
			});

		}
	}
}
