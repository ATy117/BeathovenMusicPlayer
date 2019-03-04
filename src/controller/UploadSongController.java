package controller;

import dbservice.AlbumDAO;
import dbservice.AlbumDAOLocal;
import dbservice.SongDAO;
import dbservice.SongDAOLocal;
import javafx.stage.Stage;
import model_rework.*;
import view.UploadSongsView;

import java.io.File;
import java.sql.Connection;

public class UploadSongController {

	private Stage uploadStage;
	private ProfileModel profilemodel;
	private LibraryModel librarymodel;

	public UploadSongController(ProfileModel profilemodel, LibraryModel librarymodel, Connection connection) {

		this.profilemodel = profilemodel;
		this.librarymodel = librarymodel;

		uploadStage = new Stage();

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
    }

    
	public boolean uploadSong(String song_title, String artist_name, String album_name, String genre, String year, File file ) {
		User user = profilemodel.getUser();
		int user_id = user.getUser_id();
		SongDAO sd = new SongDAOLocal();
		AlbumDAO ad = new AlbumDAOLocal();

		if (sd.checkSong(user_id, song_title, artist_name)){
			System.out.println("Existing");
		} else {
			String legit = album_name.replaceAll("[^a-zA-Z]+", "");
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
				System.out.println("No Album");
				return true;
			} else {
				int album_id = ad.checkAlbum(user_id, album_name, artist_name);
				System.out.println(album_id);
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
					System.out.println("Existing Album");
				} else {
					AlbumBuilder albumBuilder = new AlbumBuilder();
					Album newAlbum = albumBuilder
							.withName(album_name)
							.withArtist(artist_name)
							.withArtistID(-1)
							.withFileCover(null)
							.withOwner(user_id)
							.build();
					int newAlbumID = newAlbum.getAlbum_id();
					System.out.println(newAlbumID);
					SongBuilder newbuilder = new SongBuilder();
					Song song = newbuilder
							.withAlbumID(newAlbumID)
							.withName(song_title)
							.withArtistName(artist_name)
							.withGenre(genre)
							.withOwner(user_id)
							.withFavoriteStatus(false)
							.withTimesPlayed(0)
							.withFile(file)
							.withYear(Integer.parseInt(year))
							.build();

					ad.addAlbum(newAlbum);
					sd.addSong(song);
					System.out.println("New Album");
					System.out.println(sd.getAllSong(user_id));
					return true;
				}
			}
		}
		librarymodel.setSongList(sd.getAllSong(user_id));
		uploadStage.close();
		return true;
	}
}
