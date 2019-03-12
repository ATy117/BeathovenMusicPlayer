package comparatorServices;

import dbservice.AlbumDAO;
import dbservice.AlbumDAODB;
import dbservice.dbConnection;
import model_rework.Album;
import model_rework.Song;

import java.beans.PropertyVetoException;
import java.sql.Connection;

public class SongComparatorByAlbum implements SongComparator {
    @Override
    public int compare(Song o1, Song o2) {
        int id1 = o1.getAlbum_id();
        int id2 = o2.getAlbum_id();

        if (id1 == -1){
            return -1;
        } else if (id2 == -1) {
            return 1;
        } else {
            dbConnection connection = new dbConnection();
            Connection c = connection.getConnection();
            AlbumDAO AD = new AlbumDAODB(c);
            Album album1 = AD.getAlbum(id1);
            Album album2 = AD.getAlbum(id2);
            return album1.getName().compareTo(album2.getName());
        }

    }

    public static SongComparatorByAlbum getInstance(){
        return SongComparatorByAlbum.getInstance();
    }
}
