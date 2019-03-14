package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import controller.FileUploader;
import controller.MP3Uploader;
import controller.StageManager;
import controller.UploadSongController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class UploadSongsView{

	Stage primaryStage;
	UploadSongController controller;
	File musicfile = null;

	@FXML public JFXTextField titleField;
	@FXML public  JFXTextField artistField;
	@FXML public JFXTextField albumField;
	@FXML public JFXComboBox genreField;
	@FXML public JFXTextField yearField;
	@FXML public JFXButton uploadBtn;
	@FXML public JFXButton doneBtn;
	@FXML public AnchorPane addSongAnchor;

	public JFXPopup errorPopup = new JFXPopup();
	public AnchorPane errorAnchor = new AnchorPane();

	public UploadSongsView (Stage primaryStage, UploadSongController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadSongsTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Upload songs");
		init();
	}

	public void uploadSongFile(javafx.event.ActionEvent actionEvent) {
		FileUploader file = new MP3Uploader(primaryStage);
		musicfile = file.getUploadedFile();
		if(musicfile != null){
			addSongAnchor.getStylesheets().add("view/theme.css");
			uploadBtn.setText("File Uploaded!");
		}
	}


	public void addEditSongDetails(javafx.event.ActionEvent actionEvent) {
		String songTitle = titleField.getText();
		String artistName = artistField.getText();
		String album = albumField.getText();

		String genre = "";
		boolean nullGenre = false;
		if (genreField.getValue() == null){
			nullGenre = true;
		} else {
			genre = genreField.getValue().toString();
		}

		String year = yearField.getText();
		String yearCheck = year.replaceAll("\\s+", "");
		boolean badYear = false;
		if (yearCheck.equals("")){
			badYear = true;
		} else {
			try {
				int i = Integer.parseInt(year);
			} catch (NumberFormatException | NullPointerException exception) {
				badYear = true;
			}
		}
		

		String songCheck = songTitle.replaceAll("\\s+", "");
		String artistCheck = artistName.replaceAll("\\s+", "");


		if (songCheck.equals("") || artistCheck.equals("") || nullGenre || badYear || musicfile == null){
			System.out.println("make sure all fields are filled");

			errorAnchor.getStylesheets().add("view/theme.css");
			errorAnchor.getStyleClass().add("anchorPane-Error");

			Image error = new Image("resources/error.png");
			ImageView errorView = new ImageView(error);
			Text errorMessage = new Text("Make sure all fields are Filled");
			errorMessage.getStyleClass().add("text-input-Error");
			AnchorPane.setTopAnchor(errorMessage, 93.0);
			AnchorPane.setLeftAnchor(errorMessage, 20.0);
			AnchorPane.setTopAnchor(errorView, 30.0);
			AnchorPane.setLeftAnchor(errorView, 70.0);
			errorAnchor.getChildren().add(errorView);
			errorAnchor.getChildren().add(errorMessage);

			errorAnchor.setMinSize(280.0, 150.0);
			errorAnchor.setMaxSize(280.0, 150.0);
			errorPopup.setPopupContent(errorAnchor);
			errorPopup.show(addSongAnchor, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);

	    	//primaryStage.close();
		} else {
			primaryStage.close();
			controller.uploadSong(songTitle, artistName, album, genre, year, musicfile);
		}
	}


	public void init(){
		addSongAnchor.getStylesheets().add("view/theme.css");
		titleField.getStyleClass().add("jfx-text-field-LogIn");
		artistField.getStyleClass().add("jfx-text-field-LogIn");
		albumField.getStyleClass().add("jfx-text-field-LogIn");
		yearField.getStyleClass().add("jfx-text-field-LogIn");
		genreField.getStyleClass().add("jfx-combo-box-Filter");
	    genreField.getItems().add("Pop");
	    genreField.getItems().add("Rock");
	    genreField.getItems().add("Country");
	    genreField.getItems().add("Hip-hop");
	    genreField.getItems().add("Classic");
	    genreField.getItems().add("Reggae");
	    genreField.getItems().add("Ballad");
	    genreField.getItems().add("EDM");
	    genreField.getItems().add("Folk");
    }

}
