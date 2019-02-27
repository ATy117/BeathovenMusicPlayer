package model;

public class DashboardModel extends Model {

	public Dashboard dashboardState;

	public Dashboard getDashboardState() {
		return dashboardState;
	}

	public void setDashboardState(Dashboard dashboardState) {
		this.dashboardState = dashboardState;
	}

	public void setCurrentSongAndPlaylist(Song s, Playlist p){
		dashboardState.setCurrentSong(s);
		dashboardState.setCurrentPlaylist(p);
		dashboardState.addCurrentPlaylistToQueue();
	}

	public void fetchNextSong(){
		dashboardState.playNextSong();
	}

	public void fetchPreviousSong(){
		dashboardState.playPreviousSong();
	}

	public void shuffle(){
		dashboardState.shuffleQueue();
	}

}
