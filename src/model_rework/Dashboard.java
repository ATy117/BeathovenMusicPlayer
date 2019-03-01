package model_rework;

import dbservice.*;

public class Dashboard {
    private User user;
    PlaylistDAO playlistDAO;
    AlbumDAO albumDAO;
    SongDAO songDAO;

    public Dashboard (GuestUser GU){
        user = GU;
        playlistDAO  = new PlaylistDAOLocal();
        albumDAO = new AlbumDAOLocal();
        songDAO = new SongDAOLocal();
    }

    public Dashboard (RegisteredUser RU){
        user = RU;
        playlistDAO  = new PlaylistDAODB();
        albumDAO = new AlbumDAODB();
        songDAO = new SongDAODB();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }

    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public AlbumDAO getAlbumDAO() {
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public SongDAO getSongDAO() {
        return songDAO;
    }

    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }
}
