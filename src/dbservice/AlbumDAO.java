package dbservice;

import model.Album;

import java.util.List;

public interface AlbumDAO {
    public boolean addAlbum(Album album);
    public boolean deleteAlbum(int album_id);
    public void updateAlbum(Album album);
    public List<Album> getAlbums(int user_id);
}