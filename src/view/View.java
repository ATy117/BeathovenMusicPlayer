package view;

import controller.DashboardController;
import javafx.stage.Stage;

public abstract class View {

	public abstract void Update();
	public DashboardController controller;
	public Stage stage;

	public View() {

	}

	public void viewProfile() {
		controller.viewProfile();
	}




}
