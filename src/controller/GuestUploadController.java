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

public class GuestUploadController extends UploadController {


	public GuestUploadController(ProfileModel profilemodel, LibraryModel librarymodel, Connection connection) {

		this.profilemodel = profilemodel;
		this.librarymodel = librarymodel;
		this.connection = connection;

		uploadStage = new Stage();

		UploadSongsView upload = new UploadSongsView(uploadStage, this);
    }

    @Override
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
					int newAlbumID = newAlbum.getAlbum_id();
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
				}
			}
		}
		librarymodel.setSongList(sd.getAllSong(user_id));
		uploadStage.close();
		return true;
	}
}
