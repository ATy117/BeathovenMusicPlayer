package model_rework;

import controller.SongPlayerController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongPlayerModel extends Model {

	private Song currentSong;
	private List<Song> currentList;
	private List<Song> finishedList;
	private boolean repeating;
	private boolean shuffle;

	public SongPlayerModel () {
		currentSong = null;
		repeating = false;
		shuffle = false;
	}


	public void playSong(List<Song> currentList) {
		this.currentList = new ArrayList<>(currentList);
		finishedList = new ArrayList<>();
		playNextSong();
		Notify();
	}

	public boolean playNextSong() {

		if (currentList.isEmpty() && repeating) {
			repeatFinishedSongs();
		}

		if (!currentList.isEmpty()) {
			finishedList.add(currentSong);
			if (!shuffle) {
				currentSong = currentList.get(0);
				currentList.remove(0);
			} else {
				int randomindex = getRandonIndexInCurList();
				currentSong = currentList.get(randomindex);
				currentList.remove(randomindex);
			}
		}
		else {
			return false;
		}

		Notify();

		return true;

	}


	private int getRandonIndexInCurList() {

		ArrayList randompool = new ArrayList();
		for (int i=0; i<currentList.size(); i++) {
			randompool.add(i);
		}
		Collections.shuffle(randompool);

		int selected = (Integer) randompool.get(0);

		return selected;
	}

	public boolean playPreviousSong() {

		if (!finishedList.isEmpty()) {
			currentList.add(0, currentSong);
			Song backsong = finishedList.get(finishedList.size() - 1);
			finishedList.remove(finishedList.size() - 1);
			currentSong = backsong;
		}
		else  {
			return false;
		}
		Notify();
		return true;
	}

	private void repeatFinishedSongs () {
		currentList = new ArrayList<>(finishedList);
		finishedList.clear();
	}

	public Song getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}

	public List<Song> getCurrentList() {
		return currentList;
	}


	public List<Song> getFinishedList() {
		return finishedList;
	}


	public boolean isRepeating() {
		return repeating;
	}

	public void setRepeating(boolean repeating) {
		this.repeating = repeating;
	}

	public boolean isShuffle() {
		return shuffle;
	}

	public void setShuffle(boolean shuffled) {
		this.shuffle = shuffled;
	}

}
