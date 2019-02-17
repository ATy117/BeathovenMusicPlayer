package sample;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class loginController {

	public AnchorPane welcomeAnchor;
	public AnchorPane registerAnchor;

	public void loginScreen(ActionEvent actionEvent) {
		welcomeAnchor.setVisible(false);
		registerAnchor.setVisible(true);
	}
}
