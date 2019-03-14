package controller;

import dbservice.AlbumDAO;
import dbservice.AlbumDAODB;
import dbservice.SongDAO;
import dbservice.SongDAODB;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model_rework.Album;
import model_rework.Song;
import model_rework.SongPlayerModel;
import view.SongPlayerView;

import java.sql.Connection;

public class SongPlayerController extends Controller{

	private SongPlayerView player;

	public SongPlayerController (SongPlayerModel songplayermodel, Connection connection, Stage playerStage) {
		this.songplayermodel = songplayermodel;
		this.connection = connection;
		this.playerStage = playerStage;
		this.playerStage.setOnCloseRequest(e -> {
			playerStage.hide();
		});

		player = new SongPlayerView(playerStage, songplayermodel, this);
		songplayermodel.Attach(player);
	}

	public void showMusicPlayer() {
		playerStage.show();
	}

	public boolean playNextSong(){
		if (songplayermodel.playNextSong()) {
			return true;
		}
		return false;
	}

	public boolean playPrevSong(){
		if (songplayermodel.playPreviousSong()) {
			return true;
		}
		return false;
	}

	public boolean toggleShuffle(){
		if (songplayermodel.isShuffle()) {
			System.out.println("shuffle is false");
			songplayermodel.setShuffle(false);
			return false;
		}
		else {
			System.out.println("shuffle is true");
			songplayermodel.setShuffle(true);
			return true;
		}
	}

	public boolean toggleRepeat(){
		if (songplayermodel.isRepeating()) {
			System.out.println("repeat is false" );
			songplayermodel.setRepeating(false);
			return false;
		}
		else {
			System.out.println("repeat is true");
			songplayermodel.setRepeating(true);
			return true;
		}
	}

	public void trackSong (Song s){
		SongDAO SD = new SongDAODB(connection);
		s.increment();
		SD.updateSong(s);
	}

	public void endPlayer() {
		player.endPlayer();
	}

	public Album getAlbumOfSong(int albumID) {
		AlbumDAO dao = new AlbumDAODB(connection);
		Album album = dao.getAlbum(albumID);
		return album;
	}

	public Image getImageFromAlbum(int album_id) {
		Image pic;

		if (album_id == -1) {
			pic = new Image("/resources/music.png");
		}
		else {
			AlbumDAO dao = new AlbumDAODB(connection);
			Album selected = dao.getAlbum(album_id);
			pic = new Image(selected.getCover_URL().toURI().toString());
		}

		return pic;
	}

}
