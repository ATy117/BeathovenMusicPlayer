package controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.LoginView;
import view.View;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class RegisteredUserController extends Controller {

	DashboardModel model;

	public RegisteredUserController(Stage primaryStage, User user) {
		this.model = new DashboardModel(user);
		this.primaryStage = primaryStage;
		View dash = new DashboardView(primaryStage, model, this);
		model.Attach(dash);
	}

	@Override
	public void viewProfile() {

	}

	@Override
	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
