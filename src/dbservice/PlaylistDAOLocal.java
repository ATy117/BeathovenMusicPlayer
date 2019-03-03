package dbservice;

import model_rework.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOLocal implements PlaylistDAO {

    private List<Playlist> playlists = new ArrayList<>();

    @Override
    public boolean addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        return true;
    }

    @Override
    public boolean deletePlaylist(int playlist_id) {
        return false;
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        return false;
    }

    @Override
    public List<Playlist> getPlaylists(int user_id) {
        return null;
    }

    @Override
    public List<Playlist> getFavoritePlaylists(int user_id) {
        return null;
    }
}
