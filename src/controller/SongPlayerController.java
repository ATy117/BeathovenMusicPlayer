package controller;

import javafx.stage.Stage;
import model_rework.Song;
import model_rework.SongPlayerModel;
import view.SongPlayerView;

import java.sql.Connection;

public class SongPlayerController {

	private Stage playerStage;
	private SongPlayerModel songplayermodel;
	private Connection connection;

	public SongPlayerController (Stage playerStage, SongPlayerModel songplayermodel, Connection connection) {
		this.songplayermodel = songplayermodel;
		this.connection = connection;
		this.playerStage = playerStage;

<<<<<<< HEAD
=======
		playerStage = new Stage();

>>>>>>> parent of 2647d75... nak ng tokwa
		SongPlayerView player = new SongPlayerView(playerStage, songplayermodel, this);
		songplayermodel.Attach(player);
	}

	public boolean playPauseSong(){
		return true;
	}

	public boolean playNextSong(){
		return true;
	}

	public boolean fastForward(){
		/*kahit 5 secs lang*/
		return true;
	}

	public boolean playPrevSong(){
		return true;
	}

	public boolean rewind(){
		return true;
	}

	public boolean shuffle(){
		return true;
	}

	public boolean repeat(){
		return true;
	}

	public void duration(){}
}
