package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.File;

public class RegisterView {

	Stage primaryStage;
	UserRegisterController controller;

	@FXML private JFXButton backBtn;
	@FXML private JFXTextField firstNameField;
	@FXML private JFXTextField lastNameField;
	@FXML private JFXTextField usernameField;
	@FXML private JFXPasswordField passwordField;
	@FXML private JFXPasswordField checkPasswordField;
	@FXML private AnchorPane registerAnchorPane;
	@FXML private Circle circlePic;

	private File file = null;

	public RegisterView (Stage primaryStage, UserRegisterController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("registerPageTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("Register an Account");

		registerAnchorPane.getStylesheets().add("view/theme.css");
		firstNameField.getStyleClass().add("jfx-text-field-Register");
		lastNameField.getStyleClass().add("jfx-text-field-Register");
		usernameField.getStyleClass().add("jfx-text-field-Register");
		passwordField.getStyleClass().add("jfx-text-field-Register");
		checkPasswordField.getStyleClass().add("jfx-text-field-Register");
	}

	public void changePane(javafx.event.ActionEvent actionEvent) {

	}

	public void uploadProfilePic(ActionEvent actionEvent) {
		FileUploader uploader = new PhotoUploader(primaryStage);
		file = uploader.getUploadedFile();
		
	}

	public void registerAccount(ActionEvent actionEvent) {

		String firstname = firstNameField.getText();
		String lastname = lastNameField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		String checkpassword = checkPasswordField.getText();

		String firstCheck = firstname.replaceAll("\\s+", "");
		String lastCheck = lastname.replaceAll("\\s+", "");
		String userCheck = username.replaceAll("\\s+", "");
		String passCheck = password.replaceAll("\\s+", "");
		String passCheckCheck = checkpassword.replaceAll("\\s+", "");

		if (file == null)
			file = new File("resources/user.png");

		if (firstCheck.equals("") || lastCheck.equals("") || userCheck.equals("") || passCheck.equals("") || passCheckCheck.equals("")){
			System.out.println("Put shit on all fields");
		} else if ( ! password.equals(checkpassword)) {
			System.out.println("Passwords do not match");
		} else {
			controller.registerUser(username, password, firstname, lastname, file);
		}

	}
}
