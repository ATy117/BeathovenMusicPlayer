package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dashboard {
    private User currentUser;
    private Profile userProfile;
    private Library library;
    private Playlist currentPlaylist = null;
    private Song currentSong = null;
    private List<Track> queue = new ArrayList<>();
    private List<Track> done = new ArrayList<>();

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

    public void addToQueue(Song s) {
        queue.add(s);
    }

    public void addCurrentPLaylistToQueue(){
        this.addPlaylistToQueue(currentPlaylist);
    }

    public void addPlaylistToQueue(Playlist p){
        List<Track> np = new ArrayList (p.getTrackList());
        queue.addAll(np);
    }

    public boolean playNextSong(){
        if (!queue.isEmpty()) {
            done.add(0, currentSong);
            currentSong = (Song) queue.get(0);
            queue.remove(0);
            return true;
        }
        return false;
    }

    public boolean playPreviousSong(){
        if (!done.isEmpty()){
            queue.add(0, currentSong);
            currentSong = (Song) done.get(0);
            done.remove(0);
            return true;
        }
        return false;
    }



}
