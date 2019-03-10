package controller;

import dbservice.PlaylistDAO;
import dbservice.PlaylistDAODB;
import dbservice.SongDAO;
import dbservice.SongDAODB;
import javafx.stage.Stage;
import model_rework.Playlist;
import model_rework.ProfileModel;
import model_rework.Song;
import view.ShowProfileView;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;

import java.sql.Connection;
import java.util.List;


public class ShowProfileController {

	private Stage profileStage;
	private ProfileModel profilemodel;
	private Connection connection;

	public ShowProfileController(ProfileModel profilemodel, Connection connection, Stage profileStage) {
		this.profilemodel = profilemodel;
		this.connection = connection;
		this.profileStage=profileStage;

		SongDAO SD = new SongDAODB(connection);
		PlaylistDAO PD = new PlaylistDAODB(connection);

		List<Song> faveSongs = SD.getFavoriteSong(profilemodel.getUser().getUser_id());
		List<Playlist> favePlaylists = PD.getFavoritePlaylists(profilemodel.getUser().getUser_id());
		profilemodel.setFavoriteSongs(faveSongs);
		profilemodel.setFavoritePlaylists(favePlaylists);

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
