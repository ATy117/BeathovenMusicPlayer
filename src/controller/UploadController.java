package controller;

import javafx.stage.Stage;
import model_rework.LibraryModel;
import model_rework.ProfileModel;

import java.io.File;
import java.sql.Connection;

public abstract class UploadController {

	protected Stage uploadStage;
	protected ProfileModel profilemodel;
	protected LibraryModel librarymodel;
	protected Connection connection;

	public abstract boolean uploadSong(String song_title, String artist_name, String album_name, String genre, String year, File file);
}
