package controller;

import javafx.stage.Stage;
import model_rework.ProfileModel;
import view.ShowProfileView;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;

import java.sql.Connection;


public class ShowProfileController {

	private Stage profileStage;
	private ProfileModel profilemodel;
	private Connection connection;

	public ShowProfileController(ProfileModel profilemodel, Connection connection, Stage profileStage) {
		this.profilemodel = profilemodel;
		this.connection = connection;
		this.profileStage=profileStage;

		ShowProfileView view = new ShowProfileView(profileStage, profilemodel, this);
		profilemodel.Attach(view);
    }

    public void backToDashboard() {
		profileStage.close();
    }

    public void logout() {
		// dont forget save if ever
    }

    public boolean editUserDetails(){
        return true;
    }

}
