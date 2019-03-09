package dbservice;

import model_rework.Song;

import java.util.List;

public interface SongDAO {
    public boolean addSong (Song song);
    public boolean addSongToPlaylist (int song_id, int playlist_id);
    public boolean addSongToAlbum(int song_id, int album_id);

    public boolean deleteSong (int song_id);
    public boolean deleteSongFromPlaylist (int song_id, int playlist_id);
    public boolean deleteSongFromAlbum (int song_id, int album_id);

    public void updateSong (Song song);

    public Song getSong(int song_id);
    public List<Song> getAllSong(int user_id);
    public List<Song> getPlaylistSong(int user_id, int playlist_id);
    public List<Song> getAlbumSong(int user_id, int album_id);
    public List<Song> getFavoriteSong(int user_id);
    public Song getMostPlayed(int user_id);

    public int checkSong(int user_id, String song_name, String artist_name);
    public boolean checkSongPlaylist(int user_id, int song_id, int playlist_id);
}
