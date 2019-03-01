package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongPlayer {
    private Playlist currentPlaylist;
    private Song currentSong;

    private List<Track> queue = new ArrayList<>();
    private List<Track> done = new ArrayList<>();

    public SongPlayer(Playlist p){
        List<Song> newSongs = new ArrayList(p.getTrackList());
        currentPlaylist = new Playlist(p.getCollectionName(), newSongs);
        currentSong = (Song) currentPlaylist.getTrack(0);
    }

}
