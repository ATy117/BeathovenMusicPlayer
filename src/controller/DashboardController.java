package controller;

import comparatorServices.*;
import dbservice.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model_rework.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		if (player == null) {
			SongPlayerController player = new SongPlayerController(songplayermodel, connection, playerStage);
		}
		else {
			player.showMusicPlayer();
		}
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

	public void deleteSongFromPlaylist(int user_id, int song_id, String playlist_name){
		PlaylistDAO PD = new PlaylistDAODB(connection);
		SongDAO SD = new SongDAODB(connection);

		int playlist_id = PD.checkPlaylist(user_id, playlist_name);
		SD.deleteSongFromPlaylist(song_id, playlist_id);

		librarymodel.setSongList(SD.getPlaylistSong(user_id,playlist_id));
	}

	public void deletePlaylist(int user_id, int playlist_id){
		PlaylistDAO PD = new PlaylistDAODB(connection);
		SongDAO SD = new SongDAODB(connection);

		PD.deletePlaylist(playlist_id);

		librarymodel.setPlaylistList(PD.getPlaylists(user_id));
		librarymodel.setSongList(SD.getAllSong(user_id));
	}

	public void editAlbum(Album a){
		FileUploader uploader = new PhotoUploader(primaryStage);
		File photoFile = uploader.getUploadedFile();

		if (photoFile == null)
			photoFile = a.getCover_URL();
		else
			a.setCover_URL(photoFile);

		AlbumDAO AD = new AlbumDAODB(connection);

		AD.updateAlbum(a);

		librarymodel.setAlbumList(AD.getAlbums(a.getUser_id()));
	}

	public void editSong(Song s){
		SongDAO SD = new SongDAODB(connection);
		SD.updateSong(s);
		librarymodel.setSongList(SD.getAllSong(s.getUploader_id()));
		profilemodel.setFavoriteSongs(SD.getFavoriteSong(s.getUploader_id()));
	}

	public void editPlaylist(Playlist p){
		PlaylistDAO PD = new PlaylistDAODB(connection);
		PD.updatePlaylist(p);
		librarymodel.setPlaylistList(PD.getPlaylists(p.getUser_id()));
		profilemodel.setFavoritePlaylists(PD.getFavoritePlaylists(p.getUser_id()));
	}

	public void sortSongs (String category){
		SongComparator comparator;
		if (category != null) {
			switch (category) {
				case "Genre":
					comparator = new SongComparatorByGenre();
					break;

				case "Title":
					comparator = new SongComparatorByTitle();
					break;

				case "Year":
					comparator = new SongComparatorByYear();
					break;

				case "Artist":
					comparator = new SongComparatorByArtist();
					break;

				case "Album":
					comparator = new SongComparatorByAlbum();
					break;

				default:
					comparator = new SongComparatorByTitle();
					break;
			}
			ArrayList<Song> songs = (ArrayList<Song>)librarymodel.getSongList();
			Collections.sort(songs, comparator);
			librarymodel.setSongList(songs);
		}
	}	
	public void addSongToPlaylist(int user_id, int song_id, int playlist_id){
		SongDAO SD = new SongDAODB(connection);
		if (!SD.checkSongPlaylist(user_id, song_id, playlist_id))
			SD.addSongToPlaylist(song_id, playlist_id);
	}


	public void searchSong(String word){
		System.out.println("Search: " + word);
	}

	public abstract void sayHi();

	public abstract void logout();

	public void playSong(List<Song> playableList) {

		songplayermodel.playSong(playableList);
	}

	public Image getImageFromUser(User dude) {
		Image img;

		if(dude instanceof GuestUser){
			img = new Image("/resources/user.png");
		}
		else {
			System.out.println(dude.getAvatarURL());
			img = new Image(dude.getAvatarURL().toURI().toString());
		}

		return img;
	}
}
