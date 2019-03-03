package controller;

import javafx.stage.Stage;
import model_rework.DashboardModel;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;

import java.io.IOException;

public class ShowProfileController {
    Stage primaryStage;
    StageManager sm;

    public ShowProfileController(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        ShowProfileView profileView = new ShowProfileView(primaryStage, this);
    }

    public void changeToDashboard() throws IOException{
    }

    public void changeToLogin() throws IOException{
        WelcomeController welcome = new WelcomeController(primaryStage);
        LoginView login = new LoginView(primaryStage, welcome);
    }

    public boolean editUserDetails(){
        return true;
    }
}
