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

	public File selectPhoto(){
		FileUploader uploader = new PhotoUploader(primaryStage);
		return uploader.getUploadedFile();
	}

	public void editAlbum(Album a){

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
		if(category!=null){
			switch (category){
				case "Genre":
					comparator = SongComparatorByGenre.getInstance();
					break;

				case "Title":
					comparator = SongComparatorByTitle.getInstance();
					break;

				case "Year":
					comparator = SongComparatorByYear.getInstance();
					break;

				case "Artist":
					comparator = SongComparatorByArtist.getInstance();
					break;

				case "Album":
					comparator = SongComparatorByAlbum.getInstance();
					break;

				default:
					comparator = SongComparatorByTitle.getInstance();
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
			img = new Image("/resources/useryellowbluedefaultpic.png");
		}
		else {
			img = new Image(dude.getAvatarURL().toURI().toString());
		}

		return img;
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
