package controller;

import javafx.stage.Stage;
import model_rework.LibraryModel;
import model_rework.ProfileModel;
import model_rework.SongPlayerModel;

import java.sql.Connection;

public abstract class Controller {
	protected Connection connection;
	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;

	protected Stage uploadStage;
	protected Stage playerStage;
	protected Stage profileStage;
	protected Stage primaryStage;
}
