package dbservice;

import model_rework.Song;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAODB implements SongDAO{
    private Connection connection;
    private final String TABLE = "song";
    private final String COL_SONGID = "song.song_id";
    private final String COL_SONGNAME = "song.song_name";
    private final String COL_ARTISTNAME = "song.artist_name";
    private final String COL_GENRE = "song.genre";
    private final String COL_YEAR = "song.year";
    private final String COL_ISFAVORITE = "song.is_favorite";
    private final String COL_USERID = "song.user_id";
    private final String COL_SONGURL = "song.song_url";
    private final String COL_ALBUMID = "song.album_id";
    private final String COL_TIMESPLAYED = "song.times_played";

    private final String PLCONTENTS_TABLE = "playlist_contents";
    private final String PLCONTENTS_COL_PLAYLISTID = "playlist_contents.playlist_id";
    private final String PLCONTENTS_COL_SONGID = "playlist_contents.song_id";

    public SongDAODB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addSong(Song song) {
        int songIDTemp = song.getSong_id();
        String songNameTemp = song.getSong_name();
        String aristNameTemp = song.getArtist_name();
        String genreTemp = song.getGenre();
        int yearTemp = song.getYear();
        int isFavorite = (song.isFavorite()) ? 1:0;
        byte[] songURLTemp = toBlob(song.getSong_URL());
        int userIDTemp = song.getUploader_id();
        int albumIDTemp = song.getAlbum_id();
        long timesPlayedTemp = song.getTimes_played();

        String query = "INSERT INTO " +
                this.TABLE +
                " VALUES(NULL,?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, songNameTemp);
            statement.setString(2, aristNameTemp);
            statement.setString(3, genreTemp);
            statement.setInt(4, yearTemp);
            statement.setInt(5, isFavorite);
            statement.setBytes(6, songURLTemp);
            statement.setInt(7, userIDTemp);
            statement.setInt(8, albumIDTemp);
            statement.setLong(9, timesPlayedTemp);

            statement.executeUpdate();
            statement.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSongToPlaylist(int song_id, int playlist_id) {
        String query = "INSERT INTO " + this.PLCONTENTS_TABLE + " (" +
                        this.PLCONTENTS_COL_PLAYLISTID + ", " +
                        this.PLCONTENTS_COL_SONGID + ") " +
                        "VALUES(?,?)";

        //INSERT INTO `beathovendb`.`playlist_contents` (`playlist_id`, `song_id`) VALUES ('9', '52');
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, playlist_id);
            statement.setInt(2, song_id);

            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSongToAlbum(int song_id, int album_id) {
        String query = "UPDATE " + this.TABLE + " SET " +
                        this.COL_ALBUMID + " = ? " +
                        "WHERE " + this.COL_SONGID + " = " + song_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, album_id);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSong(int song_id) {
        String query = "DELETE FROM " + this.TABLE + " WHERE " +
                        this.COL_SONGID + " = " + song_id;

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
    public boolean deleteSongFromPlaylist(int song_id, int playlist_id) {
        String query = "DELETE FROM " + this.PLCONTENTS_TABLE + " WHERE " +
                this.PLCONTENTS_COL_SONGID + " = " + song_id + " AND " +
                this.PLCONTENTS_COL_PLAYLISTID + " = " + playlist_id;

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
    public boolean deleteSongFromAlbum(int song_id, int album_id) {
        String query = "UPDATE " + this.TABLE + " SET " +
                this.COL_ALBUMID + " = " + null +
                " WHERE " + this.COL_SONGID + " = " + song_id + " AND " +
                this.COL_ALBUMID + " = " + album_id;

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
    public void updateSong(Song song) {
        int songIDTemp = song.getSong_id();
        String songNameTemp = song.getSong_name();
        String aristNameTemp = song.getArtist_name();
        String genreTemp = song.getGenre();
        int yearTemp = song.getYear();
        int isFavorite = (song.isFavorite()) ? 1:0;
        byte[] songURLTemp = toBlob(song.getSong_URL());
        int userIDTemp = song.getUploader_id();
        int albumIDTemp = song.getAlbum_id();
        long timesPlayedTemp = song.getTimes_played();

        String query = "UPDATE " + this.TABLE + " SET " +
                this.COL_SONGNAME + " = ?, " +
                this.COL_ARTISTNAME + " = ?, " +
                this.COL_GENRE + " = ?, " +
                this.COL_YEAR + " = ?, " +
                this.COL_ISFAVORITE + " = ?, " +
                this.COL_SONGURL + " = ?, " +
                this.COL_USERID + " = ?, " +
                this.COL_ALBUMID + " = ?, " +
                this.COL_TIMESPLAYED + " = ? " +
                "WHERE " + this.COL_SONGID + " = " + songIDTemp;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, songNameTemp);
            statement.setString(2, aristNameTemp);
            statement.setString(3, genreTemp);
            statement.setInt(4, yearTemp);
            statement.setInt(5, isFavorite);
            statement.setBytes(6, songURLTemp);
            statement.setInt(7, userIDTemp);
            statement.setInt(8, albumIDTemp);
            statement.setLong(9, timesPlayedTemp);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Song getSong(int song_id) {
        String query = "SELECT * FROM " + this.TABLE + " WHERE " + this.COL_SONGID + " = " + song_id;
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Song songTemp = toSong(rs);
                return songTemp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Song> getAllSong(int user_id) {
        List<Song> songsTemp = new ArrayList<>();
        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_USERID + " = " + user_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                songsTemp.add(toSong(rs));
            }

            return songsTemp;
        }catch (SQLException e){
            e.printStackTrace();
            return songsTemp;
        }
    }

    @Override
    public List<Song> getPlaylistSong(int user_id, int playlist_id) {
        List<Song> songsTemp = new ArrayList<>();
        String query = "SELECT * FROM " +
                this.TABLE + " INNER JOIN " + this.PLCONTENTS_TABLE +
                " ON " + this.COL_SONGID + " = " + this.PLCONTENTS_COL_SONGID +
                " WHERE " + COL_USERID + " = " + user_id + " AND " +
                PLCONTENTS_COL_PLAYLISTID + " = " + playlist_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                songsTemp.add(toSong(rs));
            }
            return songsTemp;
        }catch (SQLException e){
            e.printStackTrace();
            return songsTemp;
        }
    }

    @Override
    public List<Song> getAlbumSong(int user_id, int album_id) {
        List<Song> songsTemp = new ArrayList<>();

        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_USERID + " = " + user_id + " AND " +
                        this.COL_ALBUMID + " = " + album_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                songsTemp.add(toSong(rs));
            }

            return songsTemp;
        }catch (SQLException e) {
            e.printStackTrace();
            return songsTemp;
        }
    }

    @Override
    public List<Song> getFavoriteSong(int user_id) {
        List<Song> songsTemp = new ArrayList<>();

        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_ISFAVORITE + " = " + 1 + " AND " +
                        this.COL_USERID + " = " + user_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                songsTemp.add(toSong(rs));
            }

            return songsTemp;
        }catch (SQLException e) {
            e.printStackTrace();
            return songsTemp;
        }
    }

    @Override
    public Song getMostPlayed(int user_id) {
        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_TIMESPLAYED + " >= (SELECT MAX(" +
                        this.COL_TIMESPLAYED + ") FROM " + this.TABLE +
                        " WHERE " + this.COL_USERID + " = " + user_id + ")";

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Song songTemp = null;
            if (rs.next()) {
                songTemp = toSong(rs);
            }

            statement.close();
            return songTemp;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    // check song returns true if the existing song name under
    // the same user is found
    @Override
    public int checkSong(int user_id, String song_name, String artist_name) {
        String query = "SELECT * FROM " + this.TABLE + " WHERE " +
                        this.COL_USERID + " = " + user_id + " AND " +
                        this.COL_SONGNAME + " = '" + song_name + "' AND " +
                        this.COL_ARTISTNAME + " = '" + artist_name + "'";


        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(this.COL_SONGID);
            }
            return -1;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean checkSongPlaylist(int user_id, int song_id, int playlist_id) {
        String query = "SELECT * FROM " +
                        this.TABLE + " INNER JOIN " + this.PLCONTENTS_TABLE +
                        " ON " + this.COL_SONGID + " = " + this.PLCONTENTS_COL_SONGID +
                        " WHERE " + COL_USERID + " = " + user_id +
                        COL_SONGID + " = " + song_id +
                        PLCONTENTS_COL_PLAYLISTID + " = " + playlist_id;

        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private Song toSong(ResultSet rs) throws SQLException{
        Song song = new Song();

        song.setSong_id(rs.getInt(this.COL_SONGID));
        song.setSong_name(rs.getString(this.COL_SONGNAME));
        song.setArtist_name(rs.getString(this.COL_ARTISTNAME));
        song.setGenre(rs.getString(this.COL_GENRE));
        song.setYear(rs.getInt(this.COL_YEAR));
        song.setFavorite(rs.getInt(this.COL_ISFAVORITE)!=0);
        song.setSong_URL(toFile(rs));
        song.setUploader_id(rs.getInt(this.COL_USERID));
        song.setAlbum_id(rs.getInt(this.COL_ALBUMID));
        song.setTimes_played(rs.getInt(this.COL_TIMESPLAYED));

        return song;
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

    private File toFile(ResultSet rs) throws SQLException{
        File file = null;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(this.COL_SONGURL));
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
