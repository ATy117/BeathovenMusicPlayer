package dbservice;

import model_rework.Album;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAODB implements AlbumDAO{
    private Connection connection;
    private final String TABLE = "album";
    private final String COL_ALBUMID = "album.album_id";
    private final String COL_ALBUMNAME = "album.album_name";
    private final String COL_USERID = "album.user_id";
    private final String COL_COVERURL = "album.cover_url";
    private final String COL_ARTISTID = "album.artist_id";
    private final String COL_ARTISTNAME = "album.artist_name";

    public AlbumDAODB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addAlbum(Album album) {
        String albumNameTemp = album.getName();
        int userIDTemp = album.getUser_id();
        byte[] coverURLBlob = toBlob(album.getCover_URL());
        int artistIDTemp = album.getArtist_id();
        String artistNameTemp = album.getArtist_name();

        String query = "INSERT INTO " +
                this.TABLE +
                " VALUES(NULL,?,?,?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, albumNameTemp);
            statement.setInt(2, userIDTemp);
            statement.setBytes(3, coverURLBlob);
            statement.setInt(4, artistIDTemp);
            statement.setString(5, artistNameTemp);

            statement.executeUpdate();
            statement.close();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAlbum(int album_id) {
        String query = "DELETE FROM " + this.TABLE + " WHERE " + this.COL_ALBUMID + " = " + album_id;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateAlbum(Album album) {
        int albumIDTemp = album.getUser_id();
        String albumNameTemp = album.getName();
        int userIDTemp = album.getUser_id();
        byte[] coverURLBlob = toBlob(album.getCover_URL());
        int artistIDTemp = album.getArtist_id();
        String artistNameTemp = album.getName();

        String query = "UPDATE " + this.TABLE + " SET " +
                this.COL_ALBUMNAME + " = ?, " +
                this.COL_USERID + " = ?, " +
                this.COL_COVERURL + " = ?, " +
                this.COL_ARTISTID + " = ? " +
                this.COL_ARTISTNAME + " = ?, " +
                "WHERE " + this.COL_USERID + " = " + albumIDTemp;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, albumNameTemp);
            statement.setInt(2, userIDTemp);
            statement.setBytes(3, coverURLBlob);
            statement.setInt(4, artistIDTemp);
            statement.setString(5, artistNameTemp);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Album> getAlbums(int user_id) {
        List<Album> albumsTemp = new ArrayList<>();

        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_USERID + " = " + user_id;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                albumsTemp.add(toAlbum(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return albumsTemp;
        }
        return albumsTemp;
    }

    @Override
    public int checkAlbum(int user_id, String album_name, String artist_name) {
        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_USERID + " = ? AND "+
                        this.COL_ALBUMNAME + " = ? AND " +
                        this.COL_ARTISTNAME + " = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            statement.setString(2, album_name);
            statement.setString(3, artist_name);

            ResultSet rs = statement.executeQuery();
            if(rs.next() == false){
                return -1;
            }
            return rs.getInt(this.COL_ALBUMID);
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }


    private byte[] toBlob(Object object){
        byte[] stream = null;
        // This is used to convert Java objects into OutputStream
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.writeObject(object);
            stream=baos.toByteArray();
        }catch(IOException e){
            // Error in serialization
            e.printStackTrace();
        }

        return stream;
    }

    private Album toAlbum(ResultSet rs) throws SQLException{
        Album album = new Album();

        album.setAlbum_id(rs.getInt(this.COL_ALBUMID));
        album.setName(rs.getString(this.COL_ALBUMNAME));
        album.setUser_id(rs.getInt(this.COL_USERID));
        album.setCover_URL(toFile(rs));
        album.setArtist_id(rs.getInt(this.COL_ARTISTID));
        album.setArtist_name(rs.getString(this.COL_ALBUMNAME));
        return album;
    }

    private File toFile(ResultSet rs) throws SQLException{
        File file = null;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(this.COL_COVERURL));
            ObjectInputStream ois = new ObjectInputStream(bais);){
            file = (File) ois.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return file;
    }
}
