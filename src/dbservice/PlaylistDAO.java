package dbservice;

import model.Playlist;

import java.util.List;

public interface PlaylistDAO {
    public boolean addPlaylist(Playlist playlist);
    public boolean deletePlaylist(Playlist id);
    public boolean updatePlaylist(Playlist playlist);
    public List<Playlist> getPlaylists(int user_id);
    public List<Playlist> getFavoritePlaylists(int user_id);

}
