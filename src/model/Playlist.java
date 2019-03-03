package model;

import java.util.List;

public class Playlist extends TrackCollection{

    public Playlist(String name){
        super.rename(name);
    }

    public Playlist(String name, List<Song> songs){
        super.rename(name);
        for (Song s : songs){
            this.addTrack(s);
        }
    }
}
