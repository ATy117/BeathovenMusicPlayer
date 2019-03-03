package controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import model.*;
import view.DashboardView;
import view.LoginView;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class MasterController extends Controller {

	Dashboard dash;
	Stage stage;
	Stage playerStage;
	Connection conn;
	//song player service

	public MasterController(Stage stage) throws IOException {
		this.stage = stage;
		LoginView login = new LoginView(this, stage);
	}

	public void loginUser(String user, String password) throws IOException {
		// if(userdaodb.check(user,password)
		//         createDashboard(userdaodb.getUser(User,Pass);
		//User dude = new RegisteredUser();

		//createDashboard(dude);

	}

	public  void playSong(int index, int user_id) {
		// List<Track> = songsdaodb.getAllSong(user_id);
		// checks which index to begin w/
		// songplayer.play(list);
	}

	public void changePane(Node button){
		//if (button == loginButton)
		//      RegisterView reg = new RegisterView(this);
	}

	public void registerAccount(String user, String password, File picture) {
		// create User u = new User();
		// userdaodb.addUser(u)
		//createDashboard(u);
	}

	private void createDashboard(User user) throws IOException {

		if (user instanceof RegisteredUser) {
			dash = new Dashboard((RegisteredUser) user);
		}
		else if (user instanceof GuestUser) {
			dash = new Dashboard((GuestUser) user);
		}

		DashboardView dashview = new DashboardView(this, dash, stage);

		// for music player pop too
		playerStage = new Stage();
		playerStage.setTitle("Music Player");
	}


	public Stage getPlayerStage() {return playerStage; }




}
