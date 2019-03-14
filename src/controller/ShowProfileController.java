package controller;

import dbservice.*;
import javafx.stage.Stage;
import model_rework.*;
import view.ShowProfileView;
import view.DashboardView;
import view.LoginView;
import view.ShowProfileView;

import java.sql.Connection;
import java.util.List;


public class ShowProfileController {

	private Stage profileStage;
	private ProfileModel profilemodel;
	private SongPlayerModel songplayermodel;
	private Connection connection;

	public ShowProfileController(ProfileModel profilemodel, SongPlayerModel songplaymodel, Connection connection, Stage profileStage) {
		this.profilemodel = profilemodel;
		this.songplayermodel = songplaymodel;
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

    public void getSongFromPlaylist(int user_id, int playlist_id){
		SongDAO SD = new SongDAODB(connection);
		profilemodel.setPlaylistSongs(SD.getPlaylistSong(user_id, playlist_id));
	}

	public boolean editUser(User RU){
		UserDAO UD = new UserDAODB(connection);
		UD.updateUser(RU);
		profilemodel.setUser(UD.getUser(RU.getUsername(), RU.getPassword()));
		return true;
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

	public void playSong(List<Song> playableList) {
		songplayermodel.playSong(playableList);
	}
}
