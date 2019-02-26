package model;

import java.io.Serializable;

public class Dashboard {
    private User currentUser;
    private Profile userProfile;
    private Library library;
    private Playlist currentPlaylist = null;
    private Song currentSong = null;

    public Dashboard (GuestUser g){
        currentUser = g;
        userProfile = null;
        library = g.getLibrary();
    }

    public Dashboard (RegisteredUser rg){
        currentUser = rg;
        userProfile = rg.getProfile();
        library = rg.getLibrary();
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public Profile getUserProfile() {
        return userProfile;
    }

    public Library getLibrary() {
        return library;
    }
}
