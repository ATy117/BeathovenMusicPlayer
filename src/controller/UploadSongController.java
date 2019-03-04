package controller;

import javafx.stage.Stage;
import view.UploadSongsView;


public class UploadSongController {

	private Stage uploadStage;

	public UploadSongController() {

		uploadStage = new Stage();

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
    }


	public boolean confirmDetails() {
		uploadStage.close();
		return true;
	}
}
