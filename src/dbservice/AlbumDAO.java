package dbservice;

import model_rework.Album;

import java.util.List;

public interface AlbumDAO {
    public boolean addAlbum(Album album);
    public boolean deleteAlbum(int album_id);
    public void updateAlbum(Album album);
    public List<Album> getAlbums(int user_id);
    public boolean checkAlbum(int user_id, String album_name, String artist_name);
}
