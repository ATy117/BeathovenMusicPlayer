package controller;

import javafx.stage.Stage;
import model_rework.*;

import java.sql.Connection;

public abstract class DashboardController {

	protected Connection connection;
	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;

	public abstract void viewProfile();
	public abstract void uploadSong();
	public abstract void sayHi();

}
