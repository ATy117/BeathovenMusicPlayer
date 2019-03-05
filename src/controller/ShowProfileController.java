package controller;

import app.Main;
import javafx.stage.Stage;
import model_rework.ProfileModel;
import view.ShowProfileView;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;

import java.sql.Connection;


public class ShowProfileController {

	private Stage profileStage;
	private Stage primaryStage;
	private ProfileModel profilemodel;
	private Connection connection;

	public ShowProfileController(Stage primaryStage, Stage profileStage, ProfileModel profilemodel, Connection connection) {
		this.profilemodel = profilemodel;
		this.connection = connection;
		this.primaryStage = primaryStage;
		this.profileStage = profileStage;

		ShowProfileView view = new ShowProfileView(profileStage, profilemodel, this);
		profilemodel.Attach(view);
    }

    public void backToDashboard() {
		profileStage.close();
    }

    public void logout() {
		primaryStage.close();

	    LoginController login = new LoginController(primaryStage);
		// dont forget save if ever
    }

    public boolean editUserDetails(){
        return true;
    }

}
