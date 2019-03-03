package model;

import java.io.Serializable;
import java.util.*;

public class Profile implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatarURL;

    private List<Song> favoriteSongs = new ArrayList<>();
    private List<Playlist> favoritePlaylists = new ArrayList<>();

    private HashMap<Song, Integer> songTracker = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public void addFavoriteSong (Song song){
        favoriteSongs.add(song);
    }

    public void removeFavoriteSong (int index){
        favoriteSongs.remove(index);
    }

    public void removeFavoriteSong (Song song){
        favoriteSongs.remove(song);
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public List<Playlist> getFavoritePlaylists() {
        return favoritePlaylists;
    }

    public void addFavoritePlaylist ( Playlist playlist){
        favoritePlaylists.add(playlist);
    }

    public void removeFavoritePlaylist ( Playlist playlist){
        favoritePlaylists.remove(playlist);
    }

    public void removeFavoritePlaylist ( int index){
        favoritePlaylists.remove(index);
    }

    public void trackSong (Song song){
        if (songTracker.containsKey(song)){
            songTracker.replace(song, songTracker.get(song) + 1);
        } else {
            songTracker.put(song, 1);
        }
    }

    public Song getMostPlayedSong(){
        return Collections.max(songTracker.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
