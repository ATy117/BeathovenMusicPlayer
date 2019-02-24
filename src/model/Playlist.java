package model;

import java.io.Serializable;

public class Playlist extends TrackCollection implements Serializable {

    public Playlist(String name){
        super.rename(name);
    }
}
