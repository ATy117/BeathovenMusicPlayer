package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.StageManager;
import controller.ShowProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model_rework.ProfileModel;

import java.io.IOException;

public class ShowProfileView extends View{

	private Stage primaryStage;
	private ShowProfileController controller;
	private ProfileModel profilemodel;

	@FXML public JFXButton backBtn;
	@FXML public JFXButton logoutBtn;
	@FXML public JFXButton editBtn;
	@FXML public JFXTextField firstNameField;
	@FXML public JFXTextField lastnameField;
	@FXML public JFXTextField usernameField;
	@FXML public JFXTextField mostPlayedField;
	@FXML public AnchorPane myProfilePane;
	@FXML public Circle profilePic;


	public ShowProfileView (Stage primaryStage, ProfileModel profilemodel, ShowProfileController controller) {

		this.primaryStage = primaryStage;
		this.controller = controller;
		this.profilemodel = profilemodel;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewProfileTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(primaryStage);
		sm.loadScene(loader);
		sm.setWindowName("My Profile");

		init();
	}

	public void init()
	{
		//myProfilePane.getStyleClass().add("view/theme.css/.text-field");
		//myProfilePane.getStylesheets().add("view/theme.css");

		if(profilemodel.getUser().getAvatarURL() == null) {
			Image defaultPic = new Image("resources/user.png");
			profilePic.setFill(new ImagePattern(defaultPic));
		}
		else{
			Image userPic = new Image(profilemodel.getUser().getAvatarURL().toString());
			profilePic.setFill(new ImagePattern(userPic));
		}

		firstNameField.setText(profilemodel.getUser().getFirst_name());
		lastnameField.setText(profilemodel.getUser().getLast_name());
		usernameField.setText(profilemodel.getUser().getUsername());
		mostPlayedField.setText("0");

		firstNameField.setEditable(false);
		lastnameField.setEditable(false);
		usernameField.setEditable(false);
		mostPlayedField.setEditable(false);
	}



	@Override
	public void Update(){}

	public void changePane(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == backBtn)
			controller.backToDashboard();
		else if (actionEvent.getSource() == logoutBtn)
			controller.logout();

	}

	public void editUserDetails(ActionEvent actionEvent) {
		controller.editUserDetails();

		if(editBtn.getText().equals("edit"))
		{
			editBtn.setText("done");
			firstNameField.setEditable(true);
			lastnameField.setEditable(true);
			usernameField.setEditable(true);
		}
		else if(editBtn.getText().equals("done"))
		{
			firstNameField.setEditable(false);
			lastnameField.setEditable(false);
			usernameField.setEditable(false);
		}

	}
}
