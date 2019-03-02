package controller;

import javafx.stage.Stage;
import view.SongPlayerView;

public class SongPlayerController {

	public SongPlayerController (Stage playerStage) {
		SongPlayerView player = new SongPlayerView(playerStage, this);
	}
}
