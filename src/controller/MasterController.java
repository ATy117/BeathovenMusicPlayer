package controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import model.*;
import view.DashboardView;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class MasterController extends Controller {

	Dashboard dash;
	Stage stage;
	Connection conn;
	//song player service

	public MasterController(Stage stage) {
		this.stage = stage;
	}

	public void loginUser(String user, String password) {
		// if(userdaodb.check(user,password)
		//         createDashboard(userdaodb.getUser(User,Pass);

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

	private void createDashboard(User user) {

		if (user instanceof RegisteredUser) {
			dash = new Dashboard((RegisteredUser) user);
		}
		else if (user instanceof GuestUser) {
			dash = new Dashboard((GuestUser) user);
		}

		DashboardView dashview = new DashboardView(this, dash);
	}

	public Stage getStage() {
		return stage;
	}




}
