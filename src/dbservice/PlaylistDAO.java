package dbservice;

import model_rework.Playlist;

import java.util.List;

public interface PlaylistDAO {
    public boolean addPlaylist(Playlist playlist);
    public boolean deletePlaylist(int playlist_id);
    public boolean updatePlaylist(Playlist playlist);
    public boolean checkPlaylist(int user_id, String playlist_name);
    public List<Playlist> getPlaylists(int user_id);
    public List<Playlist> getFavoritePlaylists(int user_id);

}
