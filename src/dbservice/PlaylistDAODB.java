package dbservice;

import model_rework.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAODB implements PlaylistDAO {
    private Connection connection;
    private final String TABLE = "playlist";
    private final String COL_PLAYLISTID = "playlist.playlist_id";
    private final String COL_USERID = "playlist.user_id";
    private final String COL_PLAYLISTNAME = "playlist.playlist_name";
    private final String COL_ISFAVORITE = "playlist.is_favorite";

    public PlaylistDAODB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addPlaylist(Playlist playlist) {
        int userIDTemp = playlist.getUser_id();
        String playlistNameTemp = playlist.getName();
        int isFavoriteTemp = (playlist.isFavorite()) ? 1 : 0;

        String query = "INSERT INTO " +
                this.TABLE +
                " VALUES(NULL,?,?,?)";

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, userIDTemp);
            statement.setString(2, playlistNameTemp);
            statement.setInt(3, isFavoriteTemp);

            statement.executeUpdate();
            statement.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePlaylist(int playlist_id) {
        String query = "DELETE FROM " + this.TABLE + " WHERE " + this.COL_PLAYLISTID + " = " + playlist_id;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        int playlistIDTemp = playlist.getPlaylist_id();
        int userIDTemp = playlist.getUser_id();
        String playlistNameTemp = playlist.getName();
        int isFavoriteTemp = (playlist.isFavorite()) ? 1 : 0;

        String query = "UPDATE " + this.TABLE + " SET " +
                this.COL_USERID + " = ?, " +
                this.COL_PLAYLISTNAME + " = ?, " +
                this.COL_ISFAVORITE + " = ?, " +
                "WHERE " + this.COL_USERID + " = " + playlistIDTemp;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userIDTemp);
            statement.setString(2, playlistNameTemp);
            statement.setInt(3, isFavoriteTemp);

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkPlaylist(int user_id, String playlist_name) {
        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                this.COL_USERID + " = ? AND "+
                this.COL_PLAYLISTNAME + " = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false){
                return true;
            }

            return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Playlist> getPlaylists(int user_id) {
        List<Playlist> playlistsTemp = new ArrayList<>();

        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                this.COL_USERID + " = " + user_id;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                playlistsTemp.add(toPlaylist(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return playlistsTemp;
        }
        return playlistsTemp;
    }

    @Override
    public List<Playlist> getFavoritePlaylists(int user_id) {
        List<Playlist> playlistsTemp = new ArrayList<>();

        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                this.COL_USERID + " = " + user_id + " AND " +
                this.COL_ISFAVORITE + " = 1";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                playlistsTemp.add(toPlaylist(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return playlistsTemp;
        }
        return playlistsTemp;
    }

    private Playlist toPlaylist(ResultSet rs) throws SQLException{
        Playlist playlist = new Playlist();

        playlist.setPlaylist_id(rs.getInt(this.COL_PLAYLISTID));
        playlist.setUser_id(rs.getInt(this.COL_USERID));
        playlist.setName(rs.getString(this.COL_PLAYLISTNAME));
        playlist.setFavorite(rs.getInt(COL_ISFAVORITE)!=0);

        return playlist;
    }
}
