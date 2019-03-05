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

	public ShowProfileController(Stage primaryStage, ProfileModel profilemodel, Connection connection) {
		this.profilemodel = profilemodel;
		this.connection = connection;
		this.primaryStage = primaryStage;
<<<<<<< HEAD
		this.profileStage = profileStage;
=======

		profileStage = new Stage();
>>>>>>> parent of 2647d75... nak ng tokwa

		ShowProfileView view = new ShowProfileView(profileStage, profilemodel, this);
		profilemodel.Attach(view);
    }

    public void backToDashboard() {
		profileStage.close();
    }

    public void logout() {
		primaryStage.close();
<<<<<<< HEAD

	    LoginController login = new LoginController(primaryStage);
=======
		Stage jesus = new Stage();
		jesus.show();

>>>>>>> parent of 2647d75... nak ng tokwa
		// dont forget save if ever
    }

    public boolean editUserDetails(){
        return true;
    }

}
