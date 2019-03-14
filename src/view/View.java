package view;

import controller.Controller;
import controller.DashboardController;
import javafx.stage.Stage;

public abstract class View {

	public abstract void Update();
	public Controller controller;
	public Stage stage;

	public View() {

	}

	public View(DashboardController controller) {
		this.controller = controller;
	}





}
