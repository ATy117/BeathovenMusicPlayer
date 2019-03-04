package controller;

import javafx.stage.Stage;
import model_rework.ProfileModel;
import view.ShowProfileView;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;


public class ShowProfileController {

	private Stage profileStage;
	private ProfileModel profilemodel;

	public ShowProfileController(ProfileModel profilemodel) {
		this.profilemodel = profilemodel;

		profileStage = new Stage();

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
