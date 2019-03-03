package controller;

import javafx.stage.Stage;
import model_rework.*;

public abstract class DashboardController {

	Stage primaryStage;
	Stage playerStage;

	protected SongPlayerModel songplayermodel;
	protected LibraryModel librarymodel;
	protected ProfileModel profilemodel;

	public abstract void viewProfile();
	public abstract void sayHi();

}