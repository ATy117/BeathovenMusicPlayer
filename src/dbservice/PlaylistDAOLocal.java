package dbservice;

import model_rework.Playlist;

import java.util.List;

public class PlaylistDAOLocal implements PlaylistDAO {
    @Override
    public boolean addPlaylist(Playlist playlist) {
        return false;
    }

    @Override
    public boolean deletePlaylist(Playlist id) {
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
