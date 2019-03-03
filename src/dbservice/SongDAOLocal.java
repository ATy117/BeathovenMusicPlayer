package dbservice;

import com.google.common.collect.ArrayListMultimap;
import model_rework.Song;

import java.util.*;
import com.google.common.collect.Multimap;

public class SongDAOLocal implements SongDAO {

    private static List<Song> songs = new ArrayList<>();
    private static Multimap<Integer, Integer> playlistSongs = new ArrayListMultimap<>();

    @Override
    public boolean checkSong(int user_id, String song_name) {
        for (Song s : songs){
            if (s.getSong_id() == user_id && s.getSong_name().equalsIgnoreCase(song_name))
                return true;
        }
        return false;
    }

    @Override
    public boolean addSong(Song song) {
        songs.add(song);
        return true;
    }

    @Override
    public boolean addSongToPlaylist(int song_id, int playlist_id) {
        playlistSongs.put(playlist_id, song_id);
        return false;
    }

    @Override
    public boolean addSongToAlbum(int song_id, int album_id) {
        for (Song s : songs){
            if (s.getSong_id() == song_id) {
                s.setAlbum_id(album_id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteSong(int song_id) {
        for (Song s : songs){
            if (s.getSong_id() == song_id){
                songs.remove(s);
                playlistSongs.values().removeAll(Collections.singleton(song_id));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteSongFromPlaylist(int song_id, int playlist_id) {
        return playlistSongs.remove(playlist_id, song_id);
    }

    @Override
    public boolean deleteSongFromAlbum(int song_id, int album_id) {
        for(Song s: songs){
            if (s.getSong_id() == song_id && s.getAlbum_id() == album_id){
                s.setAlbum_id(-1);
            }
        }
        return false;
    }

    @Override
    public void updateSong(Song song) {
        songs.remove(song);
        songs.add(song);
    }

    @Override
    public List<Song> getAllSong(int user_id) {
        List<Song> userSongs = new ArrayList<>();
        for (Song s : songs){
            if (s.getUploader_id() == user_id){
                userSongs.add(s);
            }
        }
        return userSongs;
    }

    @Override
    public List<Song> getPlaylistSong(int user_id, int playlist_id) {
        List<Song> userSongs = new ArrayList<>();
        for (Map.Entry m : playlistSongs.entries()){
            for (Song s : songs){
                if (s.getUploader_id() == user_id &&
                    (Integer) s.getSong_id() == m.getValue() &&
                    m.getKey() == (Integer) playlist_id){
                    userSongs.add(s);
                }
            }
        }
        return userSongs;
    }

    @Override
    public List<Song> getAlbumSong(int user_id, int album_id) {
        List<Song> userSongs = new ArrayList<>();
        for (Song s : songs){
            if (s.getUploader_id() == user_id && s.getAlbum_id() == album_id){
                userSongs.add(s);
            }
        }
        return userSongs;
    }

    @Override
    public List<Song> getFavoriteSong(int user_id) {
        List<Song> userSongs = new ArrayList<>();
        for (Song s : songs){
            if (s.getUploader_id() == user_id && s.isFavorite()){
                userSongs.add(s);
            }
        }
        return userSongs;
    }

    @Override
    public Song getMostPlayed(int user_id) {
        return Collections.max(songs, (o1, o2) -> {
            if (o1.getTimes_played() > o2.getTimes_played())
                return 1;
            else if (o1.getTimes_played() < o2.getTimes_played())
                return -1;
            return 0;
        });
    }

    @Override
    public boolean checkSongPlaylist(int user_id, int song_id, int playlist_id) {
        return playlistSongs.containsEntry(playlist_id, song_id);
    }
}
