package controller;

import FileUploaderService.FileUploader;
import FileUploaderService.PhotoUploader;
import dbservice.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model_rework.*;
import view.ShowProfileView;

import java.io.File;
import java.sql.Connection;
import java.util.List;


public class ShowProfileController extends Controller {

	public ShowProfileController(ProfileModel profilemodel, SongPlayerModel songplaymodel, Connection connection, Stage profileStage) {
		this.profilemodel = profilemodel;
		this.songplayermodel = songplaymodel;
		this.connection = connection;
		this.profileStage=profileStage;

		SongDAO SD = new SongDAODB(connection);
		PlaylistDAO PD = new PlaylistDAODB(connection);

		Song mostPlayed = SD.getMostPlayed(profilemodel.getUser().getUser_id());
		List<Song> faveSongs = SD.getFavoriteSong(profilemodel.getUser().getUser_id());
		List<Playlist> favePlaylists = PD.getFavoritePlaylists(profilemodel.getUser().getUser_id());
		profilemodel.setFavoriteSongs(faveSongs);
		profilemodel.setFavoritePlaylists(favePlaylists);
		profilemodel.setMostPlayedSong(mostPlayed);

		ShowProfileView view = new ShowProfileView(profileStage, profilemodel, this);
		profilemodel.Attach(view);
    }

    public void getSongFromPlaylist(int user_id, int playlist_id){
		SongDAO SD = new SongDAODB(connection);
		profilemodel.setPlaylistSongs(SD.getPlaylistSong(user_id, playlist_id));
	}

	public File selectPhoto(){
		FileUploader uploader = new PhotoUploader(profileStage);
		return uploader.getUploadedFile();
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

	public Image getImageFromUser(User user) {

		Image pic;

		if(user.getAvatarURL() == null) {
			pic = new Image("resources/user.png");
		}
		else{
			pic = new Image(user.getAvatarURL().toURI().toString());
		}

		return pic;
	}
}
