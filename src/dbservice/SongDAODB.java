package dbservice;

import model_rework.Song;

import java.util.List;

public class SongDAODB implements SongDAO{
    @Override
    public boolean addSong(Song song) {
        return false;
    }

    @Override
    public boolean addSongToPlaylist(int song_id, int playlist_id) {
        return false;
    }

    @Override
    public boolean addSongToAlbum(int song_id, int album_id) {
        return false;
    }

    @Override
    public boolean deleteSong(int song_id) {
        return false;
    }

    @Override
    public boolean deleteSongFromPlaylist(int song_id, int playlist_id) {
        return false;
    }

    @Override
    public boolean deleteSongFromAlbum(int song_id, int album_id) {
        return false;
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public List<Song> getAllSong(int user_id) {
        return null;
    }

    @Override
    public List<Song> getPlaylistSong(int user_id, int playlist_id) {
        return null;
    }

    @Override
    public List<Song> getAlbumSong(int user_id, int album_id) {
        return null;
    }

    @Override
    public List<Song> getFavoriteSong(int user_id) {
        return null;
    }

    @Override
    public Song getMostPlayed(int user_id) {
        return null;
    }

    @Override
    public boolean checkSong(int user_id, String song_name) {
        return false;
    }

    @Override
    public boolean checkSongPlaylist(int user_id, int song_id, int playlist_id) {
        return false;
    }
}
