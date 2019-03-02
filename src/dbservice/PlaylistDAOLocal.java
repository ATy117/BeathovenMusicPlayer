package dbservice;

import model_rework.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOLocal implements PlaylistDAO {

    private static List<Playlist> playlists = new ArrayList<>();

    @Override
    public boolean addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        return true;
    }

    @Override
    public boolean deletePlaylist(int playlist_id) {
        for (Playlist p : playlists){
            if (p.getPlaylist_id() == playlist_id){
                playlists.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        for (Playlist p : playlists){
            if (playlist.getPlaylist_id() == p.getPlaylist_id()){
                p.setFavorite(playlist.isFavorite());
                p.setName(playlist.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkPlaylist(int user_id, String playlist_name) {
        for (Playlist p : playlists){
            if (p.getPlaylist_id() == user_id && p.getName().equals(playlist_name))
                return true;
        }
        return false;
    }

    @Override
    public List<Playlist> getPlaylists(int user_id) {
        List<Playlist> userPlaylists = new ArrayList<>();
        for (Playlist p : playlists){
            if (p.getUser_id() == user_id){
                userPlaylists.add(p);
            }
        }
        return userPlaylists;
    }

    @Override
    public List<Playlist> getFavoritePlaylists(int user_id) {
        List<Playlist> userPlaylists = new ArrayList<>();
        for (Playlist p : playlists){
            if (p.getUser_id() == user_id && p.isFavorite() == true){
                userPlaylists.add(p);
            }
        }
        return userPlaylists;
    }
}
