package view;

import controller.Controller;
import controller.RegisteredUserController;
import javafx.stage.Stage;
import model_rework.DashboardModel;

public abstract class View {

	public abstract void Update();
	public Controller controller;
	public Stage stage;

	public View() {

	}

	public void viewProfile() {
		controller.viewProfile();
	}




}
