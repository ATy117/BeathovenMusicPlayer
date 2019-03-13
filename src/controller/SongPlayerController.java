package controller;

import dbservice.AlbumDAO;
import dbservice.AlbumDAODB;
import javafx.stage.Stage;
import model_rework.Album;
import model_rework.Song;
import model_rework.SongPlayerModel;
import view.SongPlayerView;

import java.sql.Connection;

public class SongPlayerController {

	private Stage playerStage;
	private SongPlayerModel songplayermodel;
	private Connection connection;
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

	public void shuffle(){
		if (songplayermodel.isShuffle()) {
			System.out.println("shuffle is false");
			songplayermodel.setShuffle(false);
		}
		else {
			System.out.println("shuffle is true");
			songplayermodel.setShuffle(true);
		}
	}

	public void repeat(){
		if (songplayermodel.isRepeating()) {
			System.out.println("repeat is false" );
			songplayermodel.setRepeating(false);
		}
		else {
			System.out.println("repeat is true");
			songplayermodel.setRepeating(true);
		}
	}

	public void endPlayer() {
		player.endPlayer();
	}

	public Album getAlbumOfSong(int albumID) {
		AlbumDAO dao = new AlbumDAODB(connection);
		Album album = dao.getAlbum(albumID);
		return album;
	}

}
