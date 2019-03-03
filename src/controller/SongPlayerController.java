package controller;

import javafx.stage.Stage;
import view.SongPlayerView;

public class SongPlayerController {

	public SongPlayerController (Stage playerStage) {

		SongPlayerView player = new SongPlayerView(playerStage, this);
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
