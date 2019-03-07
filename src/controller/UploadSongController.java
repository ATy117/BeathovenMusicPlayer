package controller;


import dbservice.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import model_rework.*;
import view.UploadSongsView;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.util.List;

public class UploadSongController {

	private Stage uploadStage;
	private ProfileModel profilemodel;
	private LibraryModel librarymodel;
	
	private Connection connection;


	public UploadSongController(ProfileModel profilemodel, LibraryModel librarymodel, Connection connection, Stage uploadStage) {

		this.profilemodel = profilemodel;
		this.librarymodel = librarymodel;
		this.connection = connection;
		this.uploadStage = uploadStage;

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
    }

    
	public boolean uploadSong(String song_title, String artist_name, String album_name, String genre, String year, File file ) {
		User user = profilemodel.getUser();
		int user_id = user.getUser_id();
		SongDAO sd = new SongDAODB(connection);
		AlbumDAO ad = new AlbumDAODB(connection);

		if (sd.checkSong(user_id, song_title, artist_name) != -1){
			System.out.println("Existing");
		} else {
			String legit = album_name.replaceAll("\\s+", "");
			if (legit.equals("")){
				SongBuilder builder = new SongBuilder();
				Song newSong = builder.withAlbumID(-1)
						.withName(song_title)
						.withArtistName(artist_name)
						.withGenre(genre)
						.withOwner(user_id)
						.withFavoriteStatus(false)
						.withTimesPlayed(0)
						.withFile(file)
						.withYear(Integer.parseInt(year))
						.build();
				sd.addSong(newSong);
			} else {
				int album_id = ad.checkAlbum(user_id, album_name, artist_name);
				if ( album_id != -1){
					SongBuilder builder = new SongBuilder();
					Song newSong = builder
							.withAlbumID(album_id)
							.withName(song_title)
							.withArtistName(artist_name)
							.withGenre(genre)
							.withOwner(user_id)
							.withFavoriteStatus(false)
							.withTimesPlayed(0)
							.withFile(file)
							.withYear(Integer.parseInt(year))
							.build();
					sd.addSong(newSong);
				} else {
					AlbumBuilder albumBuilder = new AlbumBuilder();
					Album newAlbum = albumBuilder
							.withName(album_name)
							.withArtist(artist_name)
							.withArtistID(-1)
							.withFileCover(null)
							.withOwner(user_id)
							.build();
					ad.addAlbum(newAlbum);
					int newAlbumID = ad.checkAlbum(user_id, album_name, artist_name);
					SongBuilder newbuilder = new SongBuilder();
					Song song = newbuilder
							.withAlbumID(-1)
							.withName(song_title)
							.withArtistName(artist_name)
							.withGenre(genre)
							.withOwner(user_id)
							.withFavoriteStatus(false)
							.withTimesPlayed(0)
							.withFile(file)
							.withYear(Integer.parseInt(year))
							.build();
					sd.addSong(song);
					int someint = sd.checkSong(user_id, song_title, artist_name);
					sd.addSongToAlbum(someint, newAlbumID);
				}
			}
		}
		librarymodel.setSongList(sd.getAllSong(user_id));
		uploadStage.close();
		return true;
	}


}
