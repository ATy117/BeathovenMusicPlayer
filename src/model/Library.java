package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private List<Song> allSongs = new ArrayList<>();
    private List<Playlist> allPlaylists = new ArrayList<>();

    public List<Song> getAllSongs() {
        return allSongs;
    }

    public List<Playlist> getAllPlaylists() {
        return allPlaylists;
    }

    public Track getSongFromLibrary(int index){
        return allSongs.get(index);
    }

    public void addSongToLibrary (Song song){
        allSongs.add(song);
    }

    public void deleteSongFromLibrary(int index){
        allSongs.remove(index);
    }

    public void deleteSongFromLibrary(Song song){
        allSongs.remove(song);
    }

    public Playlist getPlaylist(int index){
        return allPlaylists.get(index);
    }

    public void addPlaylist(Playlist playlist){
        allPlaylists.add(playlist);
    }

    public void deletePlaylist(int index){
        allPlaylists.remove(index);
    }

    public void deletePlaylist(Playlist playlist){
        allPlaylists.remove(playlist);
    }

    public Playlist getSongsAsPlaylist (){
        return new Playlist("All Songs", allSongs);
    }
}
