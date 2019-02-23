package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class songsTabController {

	public JFXListView songsListView;
	public JFXButton fileUploadButton;
	public AnchorPane songsPane;

	public void initialize() {

		fileUploadButton.getStyleClass().add("upload-button");


		// Song list renderer
		for(int i=0; i<30; i++ ) {
			HBox songCell = new HBox();
			songCell.setSpacing(20);

			Label title = new Label("Song " + i);
			Label artist = new Label ("Artist " + i);

			title.setPrefWidth(300);
			artist.setPrefWidth(270);

			Button play = new Button(" ");
			play.getStylesheets().add(getClass().getResource("theme.css").toString());
			play.getStyleClass().add("play-button");
			play.setMinHeight(32);
			play.setMinWidth(32);


			Button plus = new Button("+");

			plus.setOnMouseClicked(e -> {
				PopOver popOver = new PopOver();
				popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
				popOver.setContentNode(new Label("Test"));
				popOver.setAutoFix(true);
				popOver.setAutoHide(true);
				popOver.setHideOnEscape(true);
				popOver.setDetachable(false);
				popOver.show(plus);
			});


			songCell.getChildren().addAll(play, title, artist ,plus);
			songsListView.getItems().add(songCell);

			songCell.setOnMouseClicked(e -> {
				if(e.getButton().equals(MouseButton.PRIMARY)){
					if(e.getClickCount() == 2){
						System.out.println("Play song");
					}
				}
			});

		}
	}



	public void uploadFile(ActionEvent actionEvent) {

		String pathname = System.getProperty("user.home") + "/Documents/Beathoven";
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



}
