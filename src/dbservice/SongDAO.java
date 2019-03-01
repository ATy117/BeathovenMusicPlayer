package dbservice;

import model_rework.Song;

import java.util.List;

public interface SongDAO {
    public boolean addSong (Song song);
    public boolean deleteSong (int song_id);
    public void updateSong (Song song);
    public List<Song> getAllSong(int user_id);
    public List<Song> getPlaylistSong(int user_id, int playlist_id);
    public List<Song> getAlbumSong(int album_id);
    public List<Song> getFavoriteSong(int user_id);
    public Song getMostPlayed(int user_id);
    public boolean checkSong(int user_id, String song_name);
}
