package FileUploaderService;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MP3Uploader extends FileUploader {

	public MP3Uploader(Stage stage) {
		super(stage);
	}

	@Override
	protected void showUploaderWindow() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select mp3 files");

		File findDirectory = new File(System.getProperty("user.home")+"/Downloads");
		fileChooser.setInitialDirectory(findDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

		this.file = fileChooser.showOpenDialog(stage);
	}

}
