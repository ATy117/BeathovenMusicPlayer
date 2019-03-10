package controller;

import dbservice.*;
import javafx.stage.Stage;
import model_rework.*;

import java.sql.Connection;

public abstract class DashboardController {

	protected Connection connection;
	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;

	protected Stage uploadStage;
	protected Stage playerStage;
	protected Stage profileStage;
	protected Stage primaryStage;

	protected SongPlayerController player;

	public abstract void viewProfile();

	public void uploadSong() {
		UploadSongController upload = new UploadSongController(profilemodel, librarymodel, connection, uploadStage);
	}

	public void showSongPlayer() {
		SongPlayerController player = new SongPlayerController(songplayermodel, connection, playerStage);
	}

	public void addPlaylist(int user_id , String playlistName){
		PlaylistDAO PD = new PlaylistDAODB(connection);
		PlaylistBuilder PB = new PlaylistBuilder();
		Playlist pl = PB
				.withFavoriteStatus(false)
				.withName(playlistName)
				.withOwner(user_id)
				.build();
		if (PD.checkPlaylist(user_id, playlistName) != -1)
			System.out.println("Playlist Exists");
		else
			PD.addPlaylist(pl);

		librarymodel.setPlaylistList(PD.getPlaylists(user_id));
	}

	public void getAllSongs(int user_id){
		SongDAO SD = new SongDAODB (connection);
		librarymodel.setSongList(SD.getAllSong(user_id));
	}

	public void getAllSongsFromPlaylist(int user_id, int playlist_id){
		SongDAO SD = new SongDAODB (connection);
		librarymodel.setSongList(SD.getPlaylistSong(user_id, playlist_id));
	}

	public void getAllSongsFromAlbum(int user_id, int album_id){
		SongDAO SD = new SongDAODB (connection);
		librarymodel.setSongList(SD.getAlbumSong(user_id, album_id));
	}

	public void deleteSong(int user_id, int song_id, int album_id){
		SongDAO SD = new SongDAODB(connection);
		AlbumDAO AD = new AlbumDAODB(connection);

		SD.deleteSong(song_id);

		if (SD.getAlbumSong(user_id, album_id).isEmpty()){
			AD.deleteAlbum(album_id);
		}

		librarymodel.setSongList(SD.getAllSong(user_id));
		librarymodel.setAlbumList(AD.getAlbums(user_id));

	}

	public void editSong(Song s){
		SongDAO SD = new SongDAODB(connection);
		SD.updateSong(s);
		librarymodel.setSongList(SD.getAllSong(s.getUploader_id()));
		profilemodel.setFavoriteSongs(SD.getFavoriteSong(s.getUploader_id()));
	}

	public abstract void sayHi();

	public abstract void logout();

}
