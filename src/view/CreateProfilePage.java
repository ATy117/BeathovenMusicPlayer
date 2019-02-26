package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class CreateProfilePage {
	@FXML public JFXButton createProfButton;
	@FXML public JFXButton uploadButton;
	DashboardView parent;

	public CreateProfilePage(DashboardView parent) {
		this.parent = parent;
	}

	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == createProfButton) {
			parent.pageArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profilesTabTemplate.fxml"));
			ProfilesTabPage myprofile = new ProfilesTabPage(parent);
			loader.setController(myprofile);
			parent.pageArea.getChildren().add(loader.load());
			parent.registered=true;
		}
	}

	public void uploadProfilePic (ActionEvent actionEvent) {

		String pathname = parent.appdirectory + "/ProfilePics";
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select image");

		File songsDirectory = new File(pathname);
		if(!songsDirectory.exists()) {
			songsDirectory.mkdir();
		}

		File findDirectory = new File(System.getProperty("user.home")+"/Downloads");
		fileChooser.setInitialDirectory(findDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("PNG/JPG/JPEG Files", "*.png", "*.jpg", "*.jpeg"));

		Stage myStage = (Stage) uploadButton.getScene().getWindow();

		File file = fileChooser.showOpenDialog(myStage);

		CopyOption[] options = new CopyOption[]{
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES
		};

		if (file != null) {
			Path target = Paths.get(file.toURI());
			Path destination = Paths.get(pathname+ "/" + file.getName());
			try {
				Files.copy(target, destination, options);
			} catch (IOException ex) {
				System.out.println("File couldn't be moved");
			}
		}
	}
}
