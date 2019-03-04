package dbservice;

import model_rework.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumDAOLocal implements AlbumDAO {

    private static List<Album> albums = new ArrayList<>();

    @Override
    public boolean addAlbum(Album album) {
        albums.add(album);
        return false;
    }

    @Override
    public boolean deleteAlbum(int album_id) {
        for (Album a : albums){
            if (a.getAlbum_id() == album_id){
                albums.remove(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateAlbum(Album album) {
    }

    @Override
    public List<Album> getAlbums(int user_id) {
        List<Album> userAlbums = new ArrayList<>();
        for (Album a : albums){
            if (a.getUser_id() == user_id){
                userAlbums.add(a);
            }
        }
        return userAlbums;
    }

    @Override
    public boolean checkAlbum(int user_id, String album_name, String artist_name) {
        for (Album a : albums){
            if (a.getUser_id() == user_id &&
                a.getName().equalsIgnoreCase(album_name) &&
                a.getArtist_name().equalsIgnoreCase(artist_name))
                return true;
        }
        return false;
    }
}
