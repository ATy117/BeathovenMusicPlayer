package controller;

import javafx.stage.Stage;
import view.UploadSongsView;

public class UploadSongController {

	private Stage primaryStage;

	public UploadSongController(Stage primaryStage) {
		this.primaryStage = primaryStage;

		UploadSongsView upload = new UploadSongsView(primaryStage, this);
	}
}
