package sample;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class loginController {


	public AnchorPane registerAnchor;
	public AnchorPane welcomeAnchor;

	public void changeToRegister(ActionEvent actionEvent) {
		welcomeAnchor.setVisible(false);
		registerAnchor.setVisible(true);
	}
}
