package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class loginController {


	public AnchorPane registerAnchor;
	public AnchorPane welcomeAnchor;
	public JFXButton registerBack;

	public void changeToRegister(ActionEvent actionEvent) {
		welcomeAnchor.setVisible(false);
		registerAnchor.setVisible(true);
	}

	public void changeToWelcome(ActionEvent actionEvent) {
		registerAnchor.setVisible(false);
		welcomeAnchor.setVisible(true);
	}
}
