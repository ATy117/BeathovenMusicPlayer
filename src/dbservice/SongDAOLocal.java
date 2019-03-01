package dbservice;

import model_rework.Song;

import java.util.ArrayList;
import java.util.List;

public class SongDAOLocal implements SongDAO {

    private List<Song> songs = new ArrayList<>();

    @Override
    public boolean checkSong(int user_id, String song_name) {
        for (Song s : songs){
            if (s.getSong_id() == user_id && s.getSong_name().equals(song_name))
                return true;
        }
        return false;
    }

    @Override
    public boolean addSong(Song song) {
        songs.add(song);
        return false;
    }

    @Override
    public boolean deleteSong(int song_id) {
        for (Song s : songs){
            if (s.getSong_id() == song_id){
                songs.remove(s);
                return true;
            }
        }
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
}
