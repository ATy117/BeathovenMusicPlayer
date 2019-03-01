package model_rework;

import dbservice.AlbumDAO;
import dbservice.PlaylistDAO;
import dbservice.SongDAO;

public class Dashboard {
    private User user;
    PlaylistDAO playlistDAO;
    AlbumDAO albumDAO;
    SongDAO songsDAO;

    public Dashboard (GuestUser GU){
        user = GU;

    }
}
