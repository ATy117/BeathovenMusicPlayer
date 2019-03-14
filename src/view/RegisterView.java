package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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

	private JFXPopup errorPopup = new JFXPopup();
	private AnchorPane errorAnchor = new AnchorPane();

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
		controller.backPane();
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
			file = new File("src/resources/user.png");


		if (firstCheck.equals("") || lastCheck.equals("") || userCheck.equals("") || passCheck.equals("") || passCheckCheck.equals("")){
			popUpError("Some fields are Missing");
		} else if ( ! password.equals(checkpassword)) {
			popUpError("Passwords do not match");
		} else {
			controller.registerUser(username, password, firstname, lastname, file);
		}

	}

	public void popUpError(String inputErrorMessage)
	{
		errorAnchor.getStylesheets().add("view/theme.css");
		errorAnchor.getStyleClass().add("anchorPane-Error");

		Image error = new Image("resources/error.png");
		ImageView errorView = new ImageView(error);
		Text errorMessage = new Text(inputErrorMessage);
		errorMessage.getStyleClass().add("text-input-Error");
		AnchorPane.setTopAnchor(errorMessage, 93.0);
		AnchorPane.setLeftAnchor(errorMessage, 23.0);
		AnchorPane.setTopAnchor(errorView, 30.0);
		AnchorPane.setLeftAnchor(errorView, 43.0);
		errorAnchor.getChildren().add(errorView);
		errorAnchor.getChildren().add(errorMessage);

		errorAnchor.setMinSize(260.0, 150.0);
		errorAnchor.setMaxSize(260.0, 150.0);
		errorPopup.setPopupContent(errorAnchor);
		errorPopup.show(registerAnchorPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
	}
}
