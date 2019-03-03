package dbservice;

import model_rework.Song;

import java.util.List;

public class SongDAOLocal implements SongDAO {
<<<<<<< HEAD
=======

    private static List<Song> songs = new ArrayList<>();
    private static Multimap<Integer, Integer> playlistSongs = new ArrayListMultimap<>();

    @Override
    public boolean checkSong(int user_id, String song_name) {
        for (Song s : songs){
            if (s.getSong_id() == user_id && s.getSong_name().equalsIgnoreCase(song_name))
                return true;
        }
        return false;
    }

>>>>>>> parent of c228004... changed something sa localdao
    @Override
    public boolean addSong(Song song) {
        return false;
    }

    @Override
    public boolean deleteSong(int song_id) {
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
    public List<Song> getAlbumSong(int album_id) {
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
