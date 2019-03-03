package controller;

import javafx.stage.Stage;
import model_rework.Song;
import model_rework.SongPlayerModel;
import view.SongPlayerView;

public class SongPlayerController {

	private Stage playerStage;
	private SongPlayerModel songplayermodel;

	public SongPlayerController (Stage playerStage, SongPlayerModel songplayermodel) {
		this.songplayermodel = songplayermodel;
		this.playerStage = playerStage;

		SongPlayerView player = new SongPlayerView(playerStage, songplayermodel, this);
		songplayermodel.Attach(player);
	}
}
