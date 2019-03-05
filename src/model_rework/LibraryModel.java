package model_rework;

import java.util.ArrayList;
import java.util.List;

public class LibraryModel extends Model {
    private List<Song> songList = new ArrayList<>();
    private List<Playlist> playlistList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
        Notify();
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    public void setPlaylistList(List<Playlist> playlistList) {
        this.playlistList = playlistList;
        Notify();
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        Notify();
    }
}
