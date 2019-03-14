package FileUploaderService;

import javafx.stage.Stage;

import java.io.File;

public abstract class FileUploader {

	protected Stage stage;
	protected File file;

	public FileUploader(Stage stage) {
		this.stage = stage;
	}

	public File getUploadedFile() {
		showUploaderWindow();
		return getFile();
	}

	protected abstract void showUploaderWindow();

	private File getFile() {
		return file;
	}

	protected void setStage(Stage stage) {
		this.stage = stage;
	}
}
