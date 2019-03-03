package controller;

import javafx.stage.Stage;
import model_rework.DashboardModel;
import model_rework.Model;

public abstract class DashboardController {

	Stage primaryStage;
	Stage playerStage;

	public abstract void viewProfile();
	public abstract void sayHi();

}
