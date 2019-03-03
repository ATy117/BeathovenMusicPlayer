package model_rework;

import java.util.ArrayList;
import java.util.List;

public class ProfileModel extends Model {

    private User user;
    private List<Playlist> favoritePlaylists = new ArrayList<>();
    private List<Song> favoriteSongs = new ArrayList<>();
    private Song mostPlayedSong;

    public User getUser() {
        return user;
    }

    public List<Playlist> getFavoritePlaylists() {
        return favoritePlaylists;
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public Song getMostPlayedSong() {
        return mostPlayedSong;
    }

    public void setUser(User user) {
        this.user = user;
        Notify();
    }

    public void setFavoritePlaylists(List<Playlist> favoritePlaylists) {
        this.favoritePlaylists = favoritePlaylists;
        Notify();
    }

    public void setFavoriteSongs(List<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
        Notify();
    }

    public void setMostPlayedSong(Song mostPlayedSong) {
        this.mostPlayedSong = mostPlayedSong;
        Notify();
    }
}
