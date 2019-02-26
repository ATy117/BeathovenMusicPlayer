package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Track;
import org.controlsfx.control.PopOver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SongsTabView {

	@FXML public JFXListView songsListView;
	@FXML public JFXButton fileUploadButton;
	@FXML public JFXComboBox groupComboBox;
	DashboardView parent;

	public SongsTabView (DashboardView parent) {
		this.parent = parent;
	}

	public void initialize() {
		populateSongList();
		initGroupByCombo();
	}


	public void uploadFile(ActionEvent actionEvent) {

		String pathname = parent.appdirectory + "/Music";
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select mp3 files");

		File songsDirectory = new File(pathname);
		if(!songsDirectory.exists()) {
			songsDirectory.mkdir();
		}

		File findDirectory = new File(System.getProperty("user.home")+"/Downloads");
		fileChooser.setInitialDirectory(findDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

		Stage myStage = (Stage) fileUploadButton.getScene().getWindow();

		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(myStage);

		CopyOption[] options = new CopyOption[]{
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES
		};

		if (selectedFiles != null) {
			for (File newsong: selectedFiles) {
				Path target = Paths.get(newsong.toURI());
				Path destination = Paths.get(pathname+ "/" + newsong.getName());
				try {
					Files.copy(target, destination, options);
				} catch (IOException ex) {
					System.out.println("File couldn't be moved");
				}
			}
		}
	}

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

	public void initGroupByCombo (){
		groupComboBox.getItems().add("Genre");
		groupComboBox.getItems().add("Album");
		groupComboBox.getItems().add("Year");
	}



}