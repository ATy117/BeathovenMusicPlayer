package view;

import com.jfoenix.controls.JFXButton;
import controller.FileUploader;
import controller.PhotoUploader;
import controller.RegisterController;
import controller.StageManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class RegisterView {

	Stage primaryStage;
	RegisterController controller;

	@FXML public JFXButton backBtn;

	public RegisterView (Stage primaryStage, RegisterController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPageTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Register an Account");
	}

	public void changePane(javafx.event.ActionEvent actionEvent) {
		if (actionEvent.getSource() == backBtn) {
			controller.changeToWelcome();
		}
	}

	public void uploadProfilePic(ActionEvent actionEvent) {
		FileUploader uploader = new PhotoUploader(primaryStage);
		File file = uploader.getUploadedFile();
	}

	public void registerAccount(ActionEvent actionEvent) {
		controller.registerUser("user", "name", "pic");
	}


}
