package dbservice;

import model.Album;

import java.util.List;

public class AlbumDAODB implements AlbumDAO{

    @Override
    public boolean addAlbum(Album album) {
        return false;
    }

    @Override
    public boolean deleteAlbum(int album_id) {
        return false;
    }

    @Override
    public void updateAlbum(Album album) {

    }

    @Override
    public List<Album> getAlbums(int user_id) {
        return null;
    }
}
