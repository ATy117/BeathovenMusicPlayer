package controller;

import dbservice.PlaylistDAO;
import dbservice.PlaylistDAODB;
import dbservice.SongDAO;
import dbservice.SongDAODB;
import javafx.stage.Stage;
import model_rework.Playlist;
import model_rework.ProfileModel;
import model_rework.Song;
import model_rework.SongPlayerModel;
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
