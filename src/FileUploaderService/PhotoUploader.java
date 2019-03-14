package FileUploaderService;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PhotoUploader extends FileUploader{

	public PhotoUploader (Stage stage) {
		super(stage);
	}

	@Override
	protected void showUploaderWindow() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select picture");

		File findDirectory = new File(System.getProperty("user.home")+"/Downloads");
		fileChooser.setInitialDirectory(findDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JPEG / JPG / PNG Files", "*.jpg", "*.png", "*.jpeg"));

		this.file = fileChooser.showOpenDialog(stage);
	}

}
