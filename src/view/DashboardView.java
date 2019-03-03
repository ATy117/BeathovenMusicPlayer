package view;

import com.jfoenix.controls.JFXButton;
import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model_rework.*;

public class DashboardView extends View {

	private DashboardModel model;
	private Dashboard viewState;

	@FXML JFXButton myProfileBtn;

	public DashboardView (Stage stage, DashboardModel model, DashboardController controller) {

		super(controller);
		this.model = model;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(stage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven");
	}

	@Override
	public void Update() {

	}

	public void changePane(ActionEvent actionEvent) {
		if (actionEvent.getSource() == myProfileBtn) {
			controller.viewProfile();
		}
	}

	public void createPlaylist(ActionEvent actionEvent) {
		controller.sayHi();
	}
}

