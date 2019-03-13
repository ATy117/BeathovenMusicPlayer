package controller;

import dbservice.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import model_rework.*;
import view.DashboardView;
import view.ShowProfileView;
import view.View;

import java.sql.Connection;
import java.util.ArrayList;

public class RegisteredUserController extends DashboardController {


	public RegisteredUserController(Stage primaryStage, Connection connection, User user) {
		this.connection = connection;
		setAppDirectory();
		songplayermodel = new SongPlayerModel();
		librarymodel = new LibraryModel();
		profilemodel = new ProfileModel();

		this.primaryStage = primaryStage;
		playerStage = new Stage();
		profileStage = new Stage();
		uploadStage = new Stage();

		int user_id = user.getUser_id();

		SongDAO SD = new SongDAODB(connection);
		AlbumDAO AD = new AlbumDAODB(connection);
		PlaylistDAO PD= new PlaylistDAODB(connection);
		librarymodel.setSongList(SD.getAllSong(user_id));
		librarymodel.setPlaylistList(PD.getPlaylists(user_id));
		librarymodel.setAlbumList(AD.getAlbums(user_id));

		profilemodel.setFavoritePlaylists(PD.getFavoritePlaylists(user_id));
		profilemodel.setFavoriteSongs(SD.getFavoriteSong(user_id));
		profilemodel.setMostPlayedSong(SD.getMostPlayed(user_id));
		profilemodel.setUser(user);

		View dashboard = new DashboardView(primaryStage, songplayermodel, librarymodel, profilemodel, this);
		songplayermodel.Attach(dashboard);
		librarymodel.Attach(dashboard);
		profilemodel.Attach(dashboard);

		player = new SongPlayerController(songplayermodel, connection, playerStage);
	}

	public void viewProfile() {
		ShowProfileController profileview = new ShowProfileController(profilemodel, connection, profileStage);
	}

	public void logout() {
		playerStage.close();
		uploadStage.close();
		profileStage.close();
		//player.endPlayer();
		LoginController login = new LoginController(primaryStage);
	}

	public void sayHi() {
		System.out.println("Reg User says Hi");
	}
}
